package com.example.netlib.domain.model

data class Service(
    val id: String,
val code: String,
val name: String,
val price: Int,
val durationMin: Int,
val sort: Int,
)

data class ServiceList(
    val page: Int,
val perPage: Int,
val totalPages: Int,
val totalItems: Int,
    val items: List< Service>
)

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

data class ClinicList(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val items: List< Clinic>
)

data class DoctorExpand(
    val clinic: Clinic? = null,
)

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
val expand: DoctorExpand,
)

data class DoctorList(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val items: List< Doctor>
)