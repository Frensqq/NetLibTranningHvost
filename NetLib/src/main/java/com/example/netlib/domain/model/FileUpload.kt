package com.example.netlib.domain.model

import androidx.activity.result.contract.ActivityResultContracts

/**
 * FileUploadDTO - данные файла
 */
data class FileUpload(
    val bytes: ByteArray,
    val name: String,
    val mimeType: String = "image/jpeg"
)
