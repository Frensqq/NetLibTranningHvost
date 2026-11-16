package com.example.netlib.data.mapper

import com.example.netlib.data.remote.DTO.ClinicDTO
import com.example.netlib.data.remote.DTO.ClinicListDTO
import com.example.netlib.data.remote.DTO.DoctorDTO
import com.example.netlib.data.remote.DTO.DoctorExpandDTO
import com.example.netlib.data.remote.DTO.DoctorListDTO
import com.example.netlib.data.remote.DTO.ServiceDTO
import com.example.netlib.data.remote.DTO.ServiceListDTO
import com.example.netlib.domain.model.Clinic
import com.example.netlib.domain.model.ClinicList
import com.example.netlib.domain.model.Doctor
import com.example.netlib.domain.model.DoctorExpand
import com.example.netlib.domain.model.DoctorList
import com.example.netlib.domain.model.Service
import com.example.netlib.domain.model.ServiceList

internal fun ServiceDTO.toDomain() =
    Service(
        id = id,
        code = code,
        name = name,
        price = price,
        durationMin = durationMin,
        sort = sort
    )


internal fun ServiceListDTO.toDomain() =
    ServiceList(
        page = page,
        perPage = perPage,
        totalPages = totalPages,
        totalItems = totalItems,
        items = items.map { it.toDomain() }
    )

internal fun ClinicDTO.toDomain() =
    Clinic(
        id = id,
        name = name,
        address = address,
        phone = phone,
        lat = lat,
        lng = lng,
        hours = hours,
        sort = sort
    )


internal fun ClinicListDTO.toDomain() =
    ClinicList(
        page =  page,
        perPage = perPage,
        totalPages = totalPages ,
        totalItems = totalItems,
        items = items.map { it.toDomain() }
    )

internal fun DoctorExpandDTO.toDomain() =
    DoctorExpand(
        clinic = clinic?.toDomain()
    )


internal fun DoctorDTO.toDomain() =
    Doctor(
        id = id,
        name = name,
        specialty = specialty ,
        experienceYears = experienceYears,
        rating = rating,
        clinic = clinic,
        services = services,
        workDays = workDays,
        photo = photo,
        expand = expand.toDomain()
    )


internal fun DoctorListDTO.toDomain() =
    DoctorList(
        page = page,
        perPage = perPage,
        totalPages = totalPages,
        totalItems = totalItems,
        items = items.map { it.toDomain() }
    )