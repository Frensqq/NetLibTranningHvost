package com.example.netlib.data.remote.DTO

import kotlinx.serialization.Serializable

/**
 * FileUploadDTO - данные файла
 */
@Serializable
data class FileUploadDTO(
    val bytes: ByteArray,
    val name: String,
    val mimeType: String = "image/jpeg"
)
