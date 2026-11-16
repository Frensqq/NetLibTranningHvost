package com.example.netlib.data.remote.DTO

import android.text.BoringLayout
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
internal data class SlotDTO(
    val time: String,
val start: String,
val free: Boolean,


)

@kotlinx.serialization.Serializable
internal data class SlotsDTO(
    val doctor: String,
val date: String,
val weekday: Int,
val working: Boolean,
val slots: List<SlotDTO>,

)

@kotlinx.serialization.Serializable
internal data class AppointmentExpandDTO(
    val pet: PetDTO,
val doctor: DoctorDTO,
val service	: ServiceDTO,
val clinic: ClinicDTO,
)
@kotlinx.serialization.Serializable
internal data class AppointmentDTO(
    val id: String,
val number: Int,
val user: String,
val pet: String,
val doctor: String,
val service: String,
val clinic: String,
val start: String,
val status: String,
val comment: String,
val cancelReason: String,
val created: String,
val updated: String,
    val expand: AppointmentExpandDTO
)
@kotlinx.serialization.Serializable
internal data class AppointmentListDTO(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val items: List<AppointmentDTO>
)
@kotlinx.serialization.Serializable
internal data class AppointmentUpdateDTO(
    val pet: String? = null,
val doctor: String? = null,
val service: String? = null,
val start: String? = null,
val comment: String? = null,

)
@Serializable
internal data class CancelDTO(
    val reason: String
)