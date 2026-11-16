package com.example.netlib.data.remote.DTO

import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorDTO(
    val status: Int =0,
    val message: String = "",
    val data: Map<String, ApiErrorFieldsDTO> = emptyMap()
)

@Serializable
data class ApiErrorFieldsDTO(
    val code: String?,
    val message: String?
)
