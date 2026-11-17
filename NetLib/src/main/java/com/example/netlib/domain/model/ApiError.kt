package com.example.netlib.domain.model


/**
 * ApiError - данные ошибки
 */
data class ApiError(
    val status: Int =0,
    val message: String = "",
    val data: Map<String, ApiErrorFields> = emptyMap()
)


/**
 * ApiErrorFields - данные поля data ошибки
 */
data class ApiErrorFields(
    val code: String?,
    val message: String?
)
