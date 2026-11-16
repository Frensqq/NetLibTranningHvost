package com.example.netlib.domain.model

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

data class AuthResponse(
    val token	: String,
val record: User,
)

data class AuthRequest(
    val identity	: String,
    val password: String,
)

data class Registration(
    val email: String,
    val name: String,
    val phone: String,
    val  password: String,
    val passwordConfirm: String,
)

data class PatchUser(
    val name: String? = null,
    val phone: String? = null,
)