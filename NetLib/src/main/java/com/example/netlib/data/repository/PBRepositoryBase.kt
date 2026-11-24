package com.example.netlib.data.repository

import com.example.netlib.data.remote.ServerErrorParser
import com.example.netlib.domain.model.ApiError
import com.example.netlib.domain.model.NetworkResult
import com.example.netlib.domain.network.Network
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.serialization.ContentConvertException
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException
import java.io.IOException
import java.net.SocketTimeoutException

internal abstract class PBRepositoryBase(private val network: Network) {
    protected suspend fun <T> safeApiCall(call: suspend () -> T): NetworkResult<T> {
        if (!network.isConnected()) return NetworkResult.NoInternet
        return try {
            NetworkResult.Success(call())
        } catch (e: CancellationException) {
            throw e
        } catch (e: HttpRequestTimeoutException) {
            NetworkResult.Timeout
        } catch (e: SocketTimeoutException) {
            NetworkResult.Timeout
        } catch (e: ResponseException) {
            ServerErrorParser.parse(e.response)
        } catch (e: IOException) {
            NetworkResult.NoInternet
        } catch (e: ContentConvertException) {
            NetworkResult.ErrorSerialization(message = e.message)
        } catch (e: SerializationException) {
            NetworkResult.ErrorSerialization(message = e.message)
        } catch (e: Exception) {
            NetworkResult.Error(ApiError(message = e.message ?: "Неизвестная ошибка"))
        }
    }
}
