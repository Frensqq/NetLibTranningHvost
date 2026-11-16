package com.example.netlib.domain.model

/**
 * NetworkResult — результат выполнения сетевого запроса
 */
sealed class NetworkResult<out T>(){
    data class Success<T>(val data: T): NetworkResult<T>()

    data class Error(val errorResponse: ApiError): NetworkResult<Nothing>()
    data class ErrorSerialization(
        val status: Int? = null,
        val message: String?
    ): NetworkResult<Nothing>()


    object NoInternet: NetworkResult<Nothing>()

    object Timeout: NetworkResult<Nothing>()

}