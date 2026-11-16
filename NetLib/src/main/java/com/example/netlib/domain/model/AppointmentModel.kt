package com.example.netlib.domain.model

import android.text.BoringLayout

data class Slot(
    val time: String,
val start: String,
val free: Boolean,


)


data class Slots(
    val doctor: String,
val date: String,
val weekday: Int,
val working: Boolean,
val slots: List<Slot>,

)


data class AppointmentExpand(
    val pet: Pet,
val doctor: Doctor,
val service	: Service,
val clinic: Clinic,
)

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
    val expand: AppointmentExpand
)

data class AppointmentList(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalItems: Int,
    val items: List<Appointment>
)

data class AppointmentUpdate(
    val pet: String? = null,
val doctor: String? = null,
val service: String? = null,
val start: String? = null,
val comment: String? = null,

)

data class Cancel(
    val reason: String
)