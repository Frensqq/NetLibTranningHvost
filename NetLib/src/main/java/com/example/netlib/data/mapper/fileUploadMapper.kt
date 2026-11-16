package com.example.netlib.data.mapper

import com.example.netlib.data.remote.DTO.FileUploadDTO
import com.example.netlib.domain.model.FileUpload

internal fun FileUpload.toRequest() =
    FileUploadDTO(
        bytes = bytes,
        name = name,
        mimeType = mimeType
    )