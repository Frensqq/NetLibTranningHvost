package com.example.netlib

import com.example.netlib.data.remote.PBApi
import com.example.netlib.data.remote.PBApiService
import com.example.netlib.data.repository.AppointmentsRepositoryImpl
import com.example.netlib.data.repository.AuthRepositoryImpl
import com.example.netlib.domain.model.AppointmentUpdate
import com.example.netlib.domain.model.AuthRequest
import com.example.netlib.domain.model.NetworkResult
import com.example.netlib.domain.model.Slots
import com.example.netlib.domain.network.Network
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import org.junit.Test
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue



class RestApiMockTest {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private fun createClient(
        response: String,
        status: HttpStatusCode = HttpStatusCode.OK
    ) = PBApiService("http://10.0.2.2:8090/api/", { null }).create(MockEngine {
        respond(response, status, headersOf(HttpHeaders.ContentType, "application/json"))
    })

    private val onlineNetwork = object : Network {
        override fun isConnected(): Boolean = true
    }

    @Test
    fun authorizationSavesToken() = runTest {
        val response = """
            {
                "token": "test-token",
                "record": {
                    "id": "test-user",
                    "email": "test@mail.com",
                    "name": "Test",
                    "phone": "123456789",
                    "avatar": "",
                    "verified": true,
                    "created": "2026-01-01",
                    "updated": "2026-01-01"
                }
            }
        """.trimIndent()

        val client = createClient(response)
        val tokenStorage = FakeTokenStorage()

        val repository = AuthRepositoryImpl(
            PBApi(client),
            tokenStorage,
            onlineNetwork,

        )

        val result = repository.auth(
            AuthRequest(
                identity = "test@mail.com",
                password = "password"
            )
        )

        assertIs<NetworkResult.Success<*>>(result)
        assertEquals("test-token", tokenStorage.getToken())

        client.close()
    }

    @Test
    fun authorizationWithWrongPasswordReturnsError() = runTest {
        val response = """
            {
                "status": 400,
                "message": "Failed auth",
                "data": {}
            }
        """.trimIndent()

        val client = createClient(response, HttpStatusCode.BadRequest)
        val repository = AuthRepositoryImpl(
            PBApi(client),
            FakeTokenStorage(),
            onlineNetwork,
        )

        val result = repository.auth(
            AuthRequest(
                identity = "test@mail.com",
                password = "testPass"
            )
        )

        val error = assertIs<NetworkResult.Error>(result).errorResponse

        assertEquals(400, error.status)
        assertEquals("Failed auth", error.message)

        client.close()
    }

    @Test
    fun getSlotsReturns16Slots() = runTest {
        val slots = (0 until 16).joinToString(",") { index ->
            """
            {
                "time": "${9 + index / 2}:${if (index % 2 == 0) "00" else "30"}",
                "start": "2026-11-11T${9 + index / 2}:${if (index % 2 == 0) "00" else "30"}:00Z",
                "free": ${index % 2 == 0}
            }
            """.trimIndent()
        }

        val response = """
            {
                "doctor": "doctor-1",
                "date": "2026-11-11",
                "weekday": 3,
                "working": true,
                "slots": [$slots]
            }
        """.trimIndent()

        val client = createClient(response)
        val repository = AppointmentsRepositoryImpl(
            PBApi(client),
            onlineNetwork
        )

        val result = repository.getSlots("doctor-1", "2026-11-11")

        val data = assertIs<NetworkResult.Success<*>>(result).data
        val slotsResult = data as Slots

        assertEquals(16, slotsResult.slots.size)
        for (i in 0 until 16) {
            assertEquals(i % 2 == 0, slotsResult.slots[i].free)
        }

        client.close()
    }

    @Test
    fun occupiedSlotReturnsStartFieldError() = runTest {
        val response = """
        {
            "status": 400,
            "message": "Время занято",
            "data": {
                "start": {
                    "code": "validation_error",
                    "message": "Время занято"
                }
            }
        }
    """.trimIndent()

        val client = createClient(response, HttpStatusCode.BadRequest)
        val repository = AppointmentsRepositoryImpl(
            PBApi(client),
            onlineNetwork
        )

        val result = repository.postAppointments(
            AppointmentUpdate(
                pet = "pet-test",
                doctor = "doctor-test",
                service = "service-test",
                start = "2026-11-11T10:00:00Z",
                comment = ""
            )
        )

        val error = assertIs<NetworkResult.Error>(result).errorResponse

        assertEquals(400, error.status)
        assertTrue(error.data.containsKey("start"))
        assertEquals("validation_error", error.data["start"]?.code)
        assertEquals(
            "Время занято",
            error.data["start"]?.message
        )

        client.close()
    }

    @Test
    fun noInternetReturnsNetworkError() = runTest {
        val offlineNetwork = object : Network {
            override fun isConnected(): Boolean = false
        }

        val client = createClient("{}")
        val repository = AppointmentsRepositoryImpl(
            PBApi(client),
            offlineNetwork
        )

        val result = repository.getSlots("doctor-test", "2026-11-11")

        assertIs<NetworkResult.NoInternet>(result)

        client.close()
    }
    @Test
    fun socketTimeoutHasItsOwnResult() = runTest {
        PBApiService("http://localhost/api/", { null }).create(MockEngine {
            throw java.net.SocketTimeoutException("read timed out")
        }).use {
            val repository = AppointmentsRepositoryImpl(PBApi(it), onlineNetwork)
            assertIs<NetworkResult.Timeout>(repository.getSlots("doctor", "2026-11-11"))
        }
    }

    @Test
    fun malformedServerErrorRetainsHttpStatus() = runTest {
        createClient("<html>unavailable</html>", HttpStatusCode.BadGateway).use {
            val repository = AppointmentsRepositoryImpl(PBApi(it), onlineNetwork)
            val result = repository.getSlots("doctor", "2026-11-11")
            assertEquals(502, assertIs<NetworkResult.ErrorSerialization>(result).status)
        }
    }

    @Test
    fun cancelledRequestPropagatesCancellation() = runTest {
        PBApiService("http://localhost/api/", { null }).create(MockEngine {
            throw kotlinx.coroutines.CancellationException("cancelled")
        }).use {
            val repository = AppointmentsRepositoryImpl(PBApi(it), onlineNetwork)
            kotlin.test.assertFailsWith<kotlinx.coroutines.CancellationException> {
                repository.getSlots("doctor", "2026-11-11")
            }
        }
    }
}
