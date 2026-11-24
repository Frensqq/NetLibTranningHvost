package com.example.netlib.data.remote

import com.example.netlib.data.mapper.toDomain
import com.example.netlib.data.remote.DTO.ApiErrorDTO
import com.example.netlib.domain.model.NetworkResult
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.IOException

internal object ServerErrorParser {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun parse(response: HttpResponse): NetworkResult<Nothing> {
        val status = response.status.value
        return try {
            val error = json.decodeFromString<ApiErrorDTO>(response.bodyAsText()).toDomain()
            NetworkResult.Error(error.copy(status = status))
        } catch (e: SerializationException) {
            NetworkResult.ErrorSerialization(status, e.message)
        } catch (e: IOException) {
            NetworkResult.ErrorSerialization(status, e.message)
        }
    }
}
