package com.example.netlib.domain.model

/**
 * Service - данные сервис
 */
data class Service(
    val id: String,
val code: String,
val name: String,
val price: Int,
val durationMin: Int,
val sort: Int,
)

/**
 * ServiceList - данные список сервисов
 */
data class ServiceList(
    val page: Int,
val perPage: Int,
val totalPages: Int,
val totalItems: Int,
    val items: List< Service>
)

/**
 * Clinic - данные клиники
 */
data class Clinic(
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
 * ClinicList - данные список клиник
 */
data class ClinicList(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val items: List< Clinic>
)

/**
 * DoctorExpand - данные расширения
 */
data class DoctorExpand(
    val clinic: Clinic? = null,
)

/**
 * Doctor - данные доктор
 */
data class Doctor(
    val id: String,
val name: String,
val specialty: String,
val experienceYears: Int,
val rating: Double,
val clinic: String,
val services: List< String>,
val workDays: List< Int>,
val photo: String,
val expand: DoctorExpand? = null,
)

/**
 * DoctorList - данные список докторов
 */
data class DoctorList(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val items: List< Doctor>
)