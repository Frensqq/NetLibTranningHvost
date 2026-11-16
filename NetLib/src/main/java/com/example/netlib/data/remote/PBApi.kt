package com.example.netlib.data.remote

import com.example.netlib.data.remote.DTO.AppointmentDTO
import com.example.netlib.data.remote.DTO.AppointmentListDTO
import com.example.netlib.data.remote.DTO.AppointmentUpdateDTO
import com.example.netlib.data.remote.DTO.AuthRequestDTO
import com.example.netlib.data.remote.DTO.AuthResponseDTO
import com.example.netlib.data.remote.DTO.CancelDTO
import com.example.netlib.data.remote.DTO.ClinicListDTO
import com.example.netlib.data.remote.DTO.DoctorListDTO
import com.example.netlib.data.remote.DTO.FileUploadDTO
import com.example.netlib.data.remote.DTO.PatchUserDTO
import com.example.netlib.data.remote.DTO.PetDTO
import com.example.netlib.data.remote.DTO.PetListDTO
import com.example.netlib.data.remote.DTO.PetUpdateDTO
import com.example.netlib.data.remote.DTO.RegistrationDTO
import com.example.netlib.data.remote.DTO.ServiceListDTO
import com.example.netlib.data.remote.DTO.SlotsDTO
import com.example.netlib.data.remote.DTO.UserDTO
import com.example.netlib.domain.model.AuthRequest
import com.example.netlib.domain.model.Slots
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject


/**
 * PBApi - класс запросов к серверу
 *
 * @param client - http клиент
 *
 */
internal class PBApi(
    private val client: HttpClient
){

    private val json = Json{
        encodeDefaults = true
        explicitNulls = false

    }


    private inline suspend fun <reified D> get(
        path: String,
        perPage: Int? = null,
        page: Int? = null,
        expand: String? = null,
        filter: String? = null,
        sort: String? = null,
    ): D = client.get(path) {
        perPage?.let { "perPage" to it}
        page?.let { "page" to it}
        expand?.let { "expand" to it}
        filter?.let { "filter" to it}
        sort?.let { "sort" to it}
    }.body()

    private inline suspend fun <reified D> post(
        path: String,
        date: Any
    ): D = client.post(path) {
        setBody(date)
        contentType(ContentType.Application.Json)
    }.body()

    private inline suspend fun delete(
        path: String
    ){
        client.delete(path)
    }



    private fun files(
        vararg valeus: Pair<String, FileUploadDTO?>
    ) = valeus
        .filter { it.second != null }
        .associate { it.first to listOf(it.second!!) }


    private inline fun <reified D> fields(
         date: D
    ) : Map< String , String>{
       return  json.encodeToJsonElement(date)
            .jsonObject
            .mapNotNull { (key, value) ->
                if (value == JsonNull) null
                else key to value.let {
                    if (it is JsonPrimitive) it.content else it.toString()
                }
            }.toMap()
    }

    private inline fun <reified D> multipartBody(
        data: D,
        files: Map<String, List<FileUploadDTO>>
    ) = MultiPartFormDataContent(
        formData {
            fields(data).forEach { (key, value) ->
                append(key, value)
            }

            files.forEach { (field, list) ->
                list.forEach { file ->
                    append(
                        field,
                        file.bytes,
                        Headers.build {
                            append(
                                HttpHeaders.ContentDisposition,
                                "filename=\"${file.name}\""
                            )
                            append(HttpHeaders.ContentType, file.mimeType)
                        }
                    )
                }
            }
        }
    )

    private suspend inline fun <reified T, reified D> postMultipart(
        path: String,
        data: D,
        files: Map<String, List<FileUploadDTO>>
    ): T = client.post(path) {
        setBody(multipartBody(data, files))
    }.body()

    private suspend inline fun <reified T, reified D> patchMultipart(
        path: String,
        data: D,
        files: Map<String, List<FileUploadDTO>>
    ): T = client.patch(path) {
        setBody(multipartBody(data, files))
    }.body()


    suspend fun register(
        data: RegistrationDTO
    ): UserDTO =
        get("/api/collections/users/records")

    suspend fun auth(
        data: AuthRequestDTO
    ): AuthResponseDTO =
        post("/api/collections/users/auth-with-password", data)

    suspend fun authRefresh(): AuthResponseDTO =
        post("/api/collections/users/auth-refresh", buildJsonObject{})


    //user
    suspend fun getProfile(
        userId: String
    ): UserDTO =
        get("/api/collections/users/records/{$userId}")

    suspend fun patchProfile(
        userId: String,
        data: PatchUserDTO,
        avatar: FileUploadDTO? = null
    ): UserDTO =
        patchMultipart(
                    "/api/collections/users/records/{$userId}",
            data,
            files("avatar" to avatar)
        )

    //справочники
    suspend fun getService(
        perPage: Int?,
        page: Int?,
        sort: String?
    ): ServiceListDTO =
        get("/api/collections/services/records",
            perPage,
            page,
            sort = sort)


    suspend fun getClinics(
        perPage: Int?,
        page: Int?,
        sort: String?
    ): ClinicListDTO =
        get("/api/collections/clinics/records",
            perPage,
            page,
            sort = sort)

    suspend fun getDoctors(
        perPage: Int?,
        page: Int?,
        sort: String? = "-rating",
        filter: String?
    ): DoctorListDTO =
        get("/api/collections/doctors/records",
            perPage,
            page,
            sort = sort,
            expand = "clinic",
            filter = filter
        )



    //Питомцы

    suspend fun getPet(
        perPage: Int?,
        page: Int?,
        sort: String?
    ): PetListDTO =
        get("/api/collections/pets/records",
            perPage,
            page,
            sort = sort
        )

    suspend fun postPet(
        data: PetUpdateDTO,
        photo: FileUploadDTO? = null
    ): PetDTO =
        postMultipart(
            "/api/collections/pets/records",
            data,
            files("photo" to photo )
        )


    suspend fun patchPet(
        id: String,
        data: PetUpdateDTO,
        photo: FileUploadDTO? = null
    ): PetDTO =
        patchMultipart(
            "/api/collections/pets/records/$id",
            data,
            files("photo" to photo )
        )


    suspend fun deletePet(
        id: String
    ): Unit {
        delete("/api/collections/pets/records/${id}")
    }

    suspend fun getSlots(doctorId: String, date: String): SlotsDTO =
        client.get("hvost/slots") {
            parameter("doctor", doctorId)
            parameter("date", date)
        }.body()

    suspend fun getAppointments(
        filter: String?,
        sort: String?,
        page: Int? = 1,
        perPage: Int? = 20
    ): AppointmentListDTO = get(
        "/api/collections/appointments/records",
        expand = "pet,doctor,service,clinic",
        filter = filter,
        sort = sort,
        page = page,
        perPage = perPage
    )

    suspend fun postAppointments(
        data: AppointmentUpdateDTO
    ): AppointmentDTO =
        post(
            "/api/collections/appointments/records",
            data
        )



    suspend fun canselAppointment(
        id: String,
        data: CancelDTO,
    ): AppointmentDTO =
        post("/api/hvost/appointments/${id}/cancel",
            data)
}