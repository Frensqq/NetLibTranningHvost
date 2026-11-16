package com.example.netlib.domain.model

import android.os.Message

data class ApiError(
    val status: Int =0,
    val message: String = "",
    val data: Map<String, ApiErrorFields> = emptyMap()
)

data class ApiErrorFields(
    val code: String?,
    val message: String?
)
