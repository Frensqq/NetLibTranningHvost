package com.example.netlib.data.remote.DTO

import kotlinx.serialization.Serializable

/**
 * SlotDTO - слот из списка
 */
@kotlinx.serialization.Serializable
internal data class SlotDTO(
    val time: String,
val start: String,
val free: Boolean,


)


/**
 * SlotsDTO - список слотов
 */
@kotlinx.serialization.Serializable
internal data class SlotsDTO(
    val doctor: String,
val date: String,
val weekday: Int,
val working: Boolean,
val slots: List<SlotDTO>,

)


/**
 * AppointmentExpandDTO - расширение списка записей
 */
@kotlinx.serialization.Serializable
internal data class AppointmentExpandDTO(
    val pet: PetDTO? = null,
val doctor: DoctorDTO? = null,
val service	: ServiceDTO? = null,
val clinic: ClinicDTO? = null ,
)

/**
 * AppointmentDTO - запись
 */
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
    val expand: AppointmentExpandDTO? = null
)

/**
 * AppointmentListDTO - список записей
 */
@kotlinx.serialization.Serializable
internal data class AppointmentListDTO(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val items: List<AppointmentDTO>
)

/**
 * AppointmentUpdateDTO - обновление записей
 */
@kotlinx.serialization.Serializable
internal data class AppointmentUpdateDTO(
    val pet: String? = null,
val doctor: String? = null,
val service: String? = null,
val start: String? = null,
val comment: String? = null,

)

/**
 *
 * CancelDTO - данные для запроса Cancel
 *
 */
@Serializable
internal data class CancelDTO(
    val reason: String
)