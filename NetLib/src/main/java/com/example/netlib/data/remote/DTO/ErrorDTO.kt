package com.example.netlib.data.remote.DTO

import kotlinx.serialization.Serializable

/**
 * ApiErrorDTO - данные ошибки
 */
@Serializable
data class ApiErrorDTO(
    val status: Int =0,
    val message: String = "",
    val data: Map<String, ApiErrorFieldsDTO> = emptyMap()
)

/**
 * ApiErrorFieldsDTO - данные поля data ошибки
 */
@Serializable
data class ApiErrorFieldsDTO(
    val code: String?,
    val message: String?
)
