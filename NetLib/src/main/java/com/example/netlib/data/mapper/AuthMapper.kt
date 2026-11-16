package com.example.netlib.data.mapper

import com.example.netlib.data.remote.DTO.AuthRequestDTO
import com.example.netlib.data.remote.DTO.AuthResponseDTO
import com.example.netlib.data.remote.DTO.ClinicDTO
import com.example.netlib.data.remote.DTO.ClinicListDTO
import com.example.netlib.data.remote.DTO.DoctorDTO
import com.example.netlib.data.remote.DTO.DoctorExpandDTO
import com.example.netlib.data.remote.DTO.DoctorListDTO
import com.example.netlib.data.remote.DTO.RegistrationDTO
import com.example.netlib.data.remote.DTO.ServiceDTO
import com.example.netlib.data.remote.DTO.ServiceListDTO
import com.example.netlib.domain.model.AuthRequest
import com.example.netlib.domain.model.AuthResponse
import com.example.netlib.domain.model.Clinic
import com.example.netlib.domain.model.ClinicList
import com.example.netlib.domain.model.Doctor
import com.example.netlib.domain.model.DoctorExpand
import com.example.netlib.domain.model.DoctorList
import com.example.netlib.domain.model.Registration
import com.example.netlib.domain.model.Service
import com.example.netlib.domain.model.ServiceList

internal fun Registration.toRequest() =
    RegistrationDTO(
        email = email,
        name = name,
        phone = phone,
        password = password,
        passwordConfirm = passwordConfirm
    )


internal fun AuthResponseDTO.toDomain() =
    AuthResponse(
        token = token,
        record = record.toDomain()
    )

internal fun AuthRequest.toRequest() =
    AuthRequestDTO(
        identity = identity,
        password = password
    )