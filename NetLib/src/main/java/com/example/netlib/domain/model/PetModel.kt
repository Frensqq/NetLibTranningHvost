package com.example.netlib.domain.model

/**
 * Pet - данные питомца
 */
data class Pet(
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
 * PetList - данные список питомцев
 */
data class PetList(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val items: List< Pet>
)

/**
 * PetUpdate - данные обновления питомца
 */
data class PetUpdate(
    val name: String? = null,
    val species: String? = null,
    val breed: String? =null,
    val birthDate: String?=null,
    val weightKg: Double?=null,
    )