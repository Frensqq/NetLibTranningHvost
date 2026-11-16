package com.example.netlib.data.mapper

import com.example.netlib.data.remote.DTO.FileUploadDTO
import com.example.netlib.domain.model.FileUpload


/**
 * Перевод из FileUpload в FileUploadDTO
 */
internal fun FileUpload.toRequest() =
    FileUploadDTO(
        bytes = bytes,
        name = name,
        mimeType = mimeType
    )