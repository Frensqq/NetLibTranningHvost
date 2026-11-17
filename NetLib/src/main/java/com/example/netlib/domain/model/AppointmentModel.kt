package com.example.netlib.domain.model


/**
 * Slot - слот из списка
 */
data class Slot(
    val time: String,
val start: String,
val free: Boolean,


)

/**
 * Slots - список слотов
 */
data class Slots(
    val doctor: String,
val date: String,
val weekday: Int,
val working: Boolean,
val slots: List<Slot>,

)

/**
 * AppointmentExpand - расширение списка записей
 */
data class AppointmentExpand(
    val pet: Pet? = null,
val doctor: Doctor? = null ,
val service	: Service? = null,
val clinic: Clinic? = null,
)

/**
 * Appointment - запись
 */
data class Appointment(
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
    val expand: AppointmentExpand? = null
)

/**
 * AppointmentList - список записей
 */
data class AppointmentList(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val items: List<Appointment>
)

/**
 * AppointmentUpdate - обновление записей
 */
data class AppointmentUpdate(
    val pet: String? = null,
val doctor: String? = null,
val service: String? = null,
val start: String? = null,
val comment: String? = null,

)

/**
 *
 * Cancel - данные для запроса Cancel
 *
 */
data class Cancel(
    val reason: String
)