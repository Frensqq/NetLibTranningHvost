package com.example.netlib.data.remote.DTO

import kotlinx.serialization.Serializable

@Serializable
data class FileUploadDTO(
    val bytes: ByteArray,
    val name: String,
    val mimeType: String = "image/jpeg"
)
