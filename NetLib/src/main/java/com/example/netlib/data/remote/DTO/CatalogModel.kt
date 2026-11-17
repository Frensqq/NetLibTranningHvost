package com.example.netlib.data.remote.DTO

import kotlinx.serialization.Serializable

/**
 * ServiceDTO - данные сервис
 */
@kotlinx.serialization.Serializable
internal data class ServiceDTO(
    val id: String,
val code: String,
val name: String,
val price: Int,
val durationMin: Int,
val sort: Int,
)
/**
 * ServiceListDTO - данные список сервисов
 */
@kotlinx.serialization.Serializable
internal data class ServiceListDTO(
    val page: Int,
val perPage: Int,
val totalPages: Int,
val totalItems: Int,
    val items: List< ServiceDTO>
)
/**
 * ClinicDTO - данные клиники
 */
@kotlinx.serialization.Serializable
internal data class ClinicDTO(
    val id: String,
val name: String,
val address: String,
val phone: String,
val lat: Double,
val lng: Double,
val hours: String,
val sort: Int,
)
/**
 * ClinicListDTO - данные список клиник
 */
@kotlinx.serialization.Serializable
internal data class ClinicListDTO(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val items: List< ClinicDTO>
)

/**
 * DoctorExpandDTO - данные расширения
 */
@kotlinx.serialization.Serializable
internal data class DoctorExpandDTO(
    val clinic: ClinicDTO? = null,
)

/**
 * DoctorDTO - данные доктор
 */
@kotlinx.serialization.Serializable
internal data class DoctorDTO(
    val id: String,
val name: String,
val specialty: String,
val experienceYears: Int,
val rating: Double,
val clinic: String,
val services: List< String>,
val workDays: List< Int>,
val photo: String,
val expand: DoctorExpandDTO? = null,
)
/**
 * DoctorListDTO - данные список докторов
 */
@Serializable
internal data class DoctorListDTO(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val items: List< DoctorDTO>
)