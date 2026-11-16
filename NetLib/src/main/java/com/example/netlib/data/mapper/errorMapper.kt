package com.example.netlib.data.mapper

import com.example.netlib.data.remote.DTO.ApiErrorDTO
import com.example.netlib.data.remote.DTO.ApiErrorFieldsDTO
import com.example.netlib.domain.model.ApiError
import com.example.netlib.domain.model.ApiErrorFields

internal fun ApiErrorFieldsDTO.toDomain() =
    ApiErrorFields(
        code = code,
        message = message
    )

internal fun ApiErrorDTO.toDomain() =
    ApiError(
        status = status,
        message = message,
        data = data.mapValues { (_, error) ->
            ApiErrorFields(
                code = error.code,
                message = error.message
            )
        }
    )


