package com.example.netlib.domain.repository


import com.example.netlib.domain.model.AuthRequest
import com.example.netlib.domain.model.AuthResponse
import com.example.netlib.domain.model.NetworkResult
import com.example.netlib.domain.model.Registration
import com.example.netlib.domain.model.User
import kotlinx.serialization.json.buildJsonObject

interface AuthRepository {

    suspend fun register(
        data: Registration
    ): NetworkResult<User>

    suspend fun auth(
        data: AuthRequest
    ): NetworkResult< AuthResponse>

    suspend fun authRefresh(): NetworkResult<AuthResponse>


}