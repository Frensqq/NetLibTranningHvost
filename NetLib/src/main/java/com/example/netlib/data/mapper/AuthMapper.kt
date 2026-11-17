package com.example.netlib.data.mapper

import com.example.netlib.data.remote.DTO.AuthRequestDTO
import com.example.netlib.data.remote.DTO.AuthResponseDTO
import com.example.netlib.data.remote.DTO.RegistrationDTO
import com.example.netlib.domain.model.AuthRequest
import com.example.netlib.domain.model.AuthResponse
import com.example.netlib.domain.model.Registration

/**
 * Перевод из Registration в RegistrationDTO
 */
internal fun Registration.toRequest() =
    RegistrationDTO(
        email = email,
        name = name,
        phone = phone,
        password = password,
        passwordConfirm = passwordConfirm
    )

/**
 * Перевод из AuthResponseDTO в AuthResponse
 */
internal fun AuthResponseDTO.toDomain() =
    AuthResponse(
        token = token,
        record = record.toDomain()
    )

/**
 * Перевод из AuthRequest в AuthRequestDTO
 */
internal fun AuthRequest.toRequest() =
    AuthRequestDTO(
        identity = identity,
        password = password
    )