package com.example.netlib.data.mapper

import com.example.netlib.data.remote.DTO.ClinicDTO
import com.example.netlib.data.remote.DTO.ClinicListDTO
import com.example.netlib.data.remote.DTO.DoctorDTO
import com.example.netlib.data.remote.DTO.DoctorExpandDTO
import com.example.netlib.data.remote.DTO.DoctorListDTO
import com.example.netlib.data.remote.DTO.PetDTO
import com.example.netlib.data.remote.DTO.PetListDTO
import com.example.netlib.data.remote.DTO.PetUpdateDTO
import com.example.netlib.data.remote.DTO.ServiceDTO
import com.example.netlib.data.remote.DTO.ServiceListDTO
import com.example.netlib.domain.model.Clinic
import com.example.netlib.domain.model.ClinicList
import com.example.netlib.domain.model.Doctor
import com.example.netlib.domain.model.DoctorExpand
import com.example.netlib.domain.model.DoctorList
import com.example.netlib.domain.model.Pet
import com.example.netlib.domain.model.PetList
import com.example.netlib.domain.model.PetUpdate
import com.example.netlib.domain.model.Service
import com.example.netlib.domain.model.ServiceList

internal fun PetDTO.toDomain() =
    Pet(
        id = id,
        owner = owner ,
        name = name,
        species = species,
        breed = breed,
        birthDate = birthDate,
        weightKg = weightKg,
        photo = photo,
        created = created,
        updated = updated
    )


internal fun PetListDTO.toDomain() =
    PetList(
        page = page,
        perPage = perPage,
        totalPages = totalPages,
        totalItems = totalItems,
        items = items.map { it.toDomain() }
    )


internal fun PetUpdate.toRequest() =
    PetUpdateDTO(
        name = name,
        species = species,
        breed = breed,
        birthDate = birthDate,
        weightKg = weightKg
    )