package com.example.netlib.data.remote.DTO

import kotlinx.serialization.Serializable

/**
 * PetDTO - данные питомца
 */
@kotlinx.serialization.Serializable
internal data class PetDTO(
    val id: String,
val owner: String,
val name: String,
val species: String,
val breed: String,
val birthDate: String,
val weightKg: Double,
val photo: String,
val created: String,
val updated: String,
)
/**
 * PetListDTO - данные список питомцев
 */
@kotlinx.serialization.Serializable
internal data class PetListDTO(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val items: List< PetDTO>
)
/**
 * PetUpdateDTO - данные обновления питомца
 */
@Serializable
internal data class PetUpdateDTO(
    val name: String? = null,
    val species: String? = null,
    val breed: String? =null,
    val birthDate: String?=null,
    val weightKg: Double?=null,
    )