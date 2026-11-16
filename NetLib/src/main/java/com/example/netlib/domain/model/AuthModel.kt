package com.example.netlib.domain.model

/**
 * User - данные пользователя
 */
data class User(
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
 * AuthResponse - данные ответа авторизации
 */
data class AuthResponse(
    val token	: String,
val record: User,
)

/**
 * AuthRequest - данные запроса авторизации
 */
data class AuthRequest(
    val identity	: String,
    val password: String,
)

/**
 * Registration - данные регистрации
 */
data class Registration(
    val email: String,
    val name: String,
    val phone: String,
    val  password: String,
    val passwordConfirm: String,
)

/**
 * PatchUser - данные обновление пользователя
 */
data class PatchUser(
    val name: String? = null,
    val phone: String? = null,
)