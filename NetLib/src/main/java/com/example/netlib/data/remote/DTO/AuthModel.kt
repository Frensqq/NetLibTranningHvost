package com.example.netlib.data.remote.DTO

import kotlinx.serialization.Serializable

/**
 * UserDTO - данные пользователя
 */
@kotlinx.serialization.Serializable
internal data class UserDTO(
    val id: String,
val email: String,
val name: String,
val phone: String,
val avatar: String,
val verified: Boolean,
val created: String,
val updated: String,
)
/**
 * AuthResponseDTO - данные ответа авторизации
 */
@kotlinx.serialization.Serializable
internal data class AuthResponseDTO(
    val token	: String,
val record: UserDTO,
)
/**
 * AuthRequestDTO - данные запроса авторизации
 */
@kotlinx.serialization.Serializable
internal data class AuthRequestDTO(
    val identity	: String,
    val password: String,
)
/**
 * RegistrationDTO - данные регистрации
 */
@kotlinx.serialization.Serializable
internal data class RegistrationDTO(
    val email: String,
    val name: String,
    val phone: String,
    val  password: String,
    val passwordConfirm: String,
)
/**
 * PatchUserDTO - данные обновление пользователя
 */
@Serializable
internal data class PatchUserDTO(
    val name: String? = null,
    val phone: String? = null,
)