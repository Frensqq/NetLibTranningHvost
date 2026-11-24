package com.example.netlib

import com.example.netlib.data.remote.PBApi
import com.example.netlib.data.remote.PBApiService
import com.example.netlib.data.repository.*
import com.example.netlib.domain.model.*
import com.example.netlib.domain.network.Network
import java.time.LocalDate
import java.time.ZoneId
import java.util.UUID
import com.example.netlib.data.remote.KtorRealtimeClient
import com.example.netlib.data.remote.WSConnectionImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.Assume.assumeTrue
import org.junit.Test

/** Creates a disposable account on an explicitly selected local training server. */
class HvostLiveTest {
    private fun <T> NetworkResult<T>.value(): T = when (this) {
        is NetworkResult.Success -> data
        else -> error("Unexpected REST result: $this")
    }

    private suspend inline fun <reified T : WebSocketEvent> StateFlow<List<WebSocketEvent>>.awaitEvent(): T =
        withTimeout(35_000) { map { it.filterIsInstance<T>().lastOrNull() }.filterNotNull().first() }

    @Test
    fun allRestOperations() {
        val base = System.getenv("HVOST_TEST_URL")
        assumeTrue("Select a disposable local server with HVOST_TEST_URL", base != null)
        require(java.net.URI(base).host in setOf("localhost", "127.0.0.1"))
        val storage = FakeTokenStorage()
        val network = object : Network { override fun isConnected() = true }
        val factory = PBApiService(base!!, storage::getToken)
        factory.create().use { client ->
            runBlocking {
                val api = PBApi(client)
                val auth = AuthRepositoryImpl(api, storage, network)
                val users = ProfileRepositoryImpl(api, network)
                val pets = PetRepositoryImpl(api, network)
                val catalog = CatalogRepositoryImpl(api, network)
                val visits = AppointmentsRepositoryImpl(api, network)
                val email = "netlib-${UUID.randomUUID()}@example.org"
                val password = UUID.randomUUID().toString()
                val id = auth.register(Registration(email, "NetLib", "", password, password)).value().id
                assertEquals(id, auth.auth(AuthRequest(email, password)).value().record.id)
                assertEquals(id, auth.authRefresh().value().record.id)
                assertEquals(id, users.getProfile(id).value().id)
                val png = FileUpload(java.util.Base64.getDecoder().decode(
                    "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mP8/x8AAwMCAO+aAugAAAAASUVORK5CYII="
                ), "sample.png", "image/png")
                assertTrue(users.patchProfile(id, PatchUser(), png).value().avatar.isNotBlank())
                assertEquals("Новое имя", users.patchProfile(id, PatchUser(name = "Новое имя")).value().name)
                val service = catalog.getService(null, null, "sort").value().items.first()
                val clinics = catalog.getClinics(null, null, "sort").value().items
                val doctor = catalog.getDoctors(filter = "services~'${service.id}'").value().items.first()
                assertTrue(clinics.any { it.id == doctor.clinic })
                val pet = pets.postPet(PetUpdate(name = "Барсик", species = "cat")).value()
                val wsUrl = requireNotNull(System.getenv("HVOST_TEST_WS_URL"))
                require(java.net.URI(wsUrl).host in setOf("localhost", "127.0.0.1"))
                val realtime = KtorRealtimeClient(WSConnectionImpl(client, wsUrl), factory.json, storage::getToken)
                val received = MutableStateFlow(emptyList<WebSocketEvent>())
                val listener = launch(start = CoroutineStart.UNDISPATCHED) {
                    realtime.events.collect { event -> received.update { it + event } }
                }
                try {
                    realtime.connect()
                    assertEquals(id, received.awaitEvent<HelloEvent>().user.id)
                    realtime.ping()
                    received.awaitEvent<PongEvent>()
                    assertEquals(pet.id, pets.getPet(null, null, null).value().items.single().id)
                    assertTrue(pets.patchPet(pet.id, PetUpdate(), png).value().photo.isNotBlank())
                    assertEquals("Мурзик", pets.patchPet(pet.id, PetUpdate(name = "Мурзик")).value().name)
                    val today = LocalDate.now(ZoneId.of("Europe/Moscow"))
                    val date = (1L..14L).map(today::plusDays).first { it.dayOfWeek.value in doctor.workDays }
                    realtime.subscribeSlots(doctor.id, date.toString())
                    assertEquals(doctor.id, received.awaitEvent<SubscribeEvent>().doctor)
                    val slots = visits.getSlots(doctor.id, date.toString()).value().slots
                    assertEquals(16, slots.size)
                    val request = AppointmentUpdate(pet.id, doctor.id, service.id, slots.first { it.free }.start, "")
                    val visit = visits.postAppointments(request).value()
                    assertEquals(request.start, received.awaitEvent<SlotTakenEvent>().start)
                    realtime.requestQueueDemo(visit.id)
                    assertEquals(visit.id, received.awaitEvent<QueueEvent>().appointmentId)
                    assertEquals(visit.id, received.awaitEvent<DelayEvent>().appointmentId)
                    val row = visits.getAppointments(null, "start", 1, 20).value().items.single()
                    assertEquals(visit.id, row.id)
                    assertEquals(pet.id, row.expand?.pet?.id)
                    assertEquals(doctor.id, row.expand?.doctor?.id)
                    assertEquals(service.id, row.expand?.service?.id)
                    assertEquals(doctor.clinic, row.expand?.clinic?.id)
                    assertEquals("cancelled", visits.canselAppointment(visit.id, Cancel("Проверка")).value().status)
                    withTimeout(10_000) {
                        received.first { events -> events.filterIsInstance<StatusEvent>().any {
                            it.appointmentId == visit.id && it.status == "cancelled"
                        } }
                    }
                    assertEquals(request.start, received.awaitEvent<SlotFreesEvent>().start)
                    realtime.unsubscribeSlots(doctor.id, date.toString())
                    received.awaitEvent<UnsubscribeEvent>()
                } finally {
                    listener.cancel()
                    realtime.disconnect()
                    pets.deletePet(pet.id).value()
                }
                assertTrue(pets.getPet(null, null, null).value().items.isEmpty())
            }
        }
    }
}
