package com.example.netlib.domain.repository


import com.example.netlib.domain.model.AuthRequest
import com.example.netlib.domain.model.AuthResponse
import com.example.netlib.domain.model.NetworkResult
import com.example.netlib.domain.model.Registration
import com.example.netlib.domain.model.User


/**
 * AuthRepository — определяет методы авторизации, регистрации и обновления токена
 */
interface AuthRepository {

    suspend fun register(
        data: Registration
    ): NetworkResult<User>

    suspend fun auth(
        data: AuthRequest
    ): NetworkResult< AuthResponse>

    suspend fun authRefresh(): NetworkResult<AuthResponse>

    fun saveToken(
        token: String? = null
    )
    fun getToken() : String?
}