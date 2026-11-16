package com.example.netlib.data.remote

import com.example.netlib.data.remote.DTO.FileUploadDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
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
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject

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


    





}