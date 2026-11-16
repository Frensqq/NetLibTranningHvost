package com.example.netlib.data.remote.DTO

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class UserDTO(
    val id: String,
val email: String,
val name: String,
val phone: String,
val avatar: String,
val verified: Boolean,
val created: String,
val updated: String,
)
@kotlinx.serialization.Serializable
data class AuthResponseDTO(
    val token	: String,
val record: UserDTO,
)
@kotlinx.serialization.Serializable
data class AuthRequestDTO(
    val identity	: String,
    val password: String,
)
@kotlinx.serialization.Serializable
data class RegistrationDTO(
    val email: String,
    val name: String,
    val phone: String,
    val  password: String,
    val passwordConfirm: String,
)
@Serializable
data class PatchUserDTO(
    val name: String? = null,
    val phone: String? = null,
)