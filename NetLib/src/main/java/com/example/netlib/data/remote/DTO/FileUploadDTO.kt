package com.example.netlib.data.remote.DTO

import androidx.activity.result.contract.ActivityResultContracts

data class FileUploadDTO(
    val bytes: ByteArray,
    val name: String,
    val mimeType: String = "image/jpeg"
)
