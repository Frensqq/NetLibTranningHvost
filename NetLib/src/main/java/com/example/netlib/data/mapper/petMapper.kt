package com.example.netlib.data.mapper

import com.example.netlib.data.remote.DTO.PetDTO
import com.example.netlib.data.remote.DTO.PetListDTO
import com.example.netlib.data.remote.DTO.PetUpdateDTO
import com.example.netlib.domain.model.Pet
import com.example.netlib.domain.model.PetList
import com.example.netlib.domain.model.PetUpdate

/**
 * Перевод из PetDTO в Pet
 */
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

/**
 * Перевод из PetListDTO в PetList
 */
internal fun PetListDTO.toDomain() =
    PetList(
        page = page,
        perPage = perPage,
        totalPages = totalPages,
        totalItems = totalItems,
        items = items.map { it.toDomain() }
    )


/**
 * Перевод из PetUpdate в PetUpdateDTO
 */
internal fun PetUpdate.toRequest() =
    PetUpdateDTO(
        name = name,
        species = species,
        breed = breed,
        birthDate = birthDate,
        weightKg = weightKg
    )