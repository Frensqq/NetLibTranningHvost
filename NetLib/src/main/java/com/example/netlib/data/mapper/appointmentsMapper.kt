package com.example.netlib.data.mapper

import com.example.netlib.data.remote.DTO.AppointmentDTO
import com.example.netlib.data.remote.DTO.AppointmentExpandDTO
import com.example.netlib.data.remote.DTO.AppointmentListDTO
import com.example.netlib.data.remote.DTO.AppointmentUpdateDTO
import com.example.netlib.data.remote.DTO.CancelDTO
import com.example.netlib.data.remote.DTO.SlotDTO
import com.example.netlib.data.remote.DTO.SlotsDTO
import com.example.netlib.domain.model.Appointment
import com.example.netlib.domain.model.AppointmentExpand
import com.example.netlib.domain.model.AppointmentList
import com.example.netlib.domain.model.AppointmentUpdate
import com.example.netlib.domain.model.Cancel
import com.example.netlib.domain.model.Slot
import com.example.netlib.domain.model.Slots


/**
 * Перевод из SlotDTO в Slot
 */
internal fun SlotDTO.toDomain()
= Slot(
    time = time,
    start = start,
    free = free
)

/**
 * Перевод из SlotsDTO в Slots
 */
internal fun SlotsDTO.toDomain()
= Slots(
    doctor =    doctor,
    date =  date,
    weekday =  weekday ,
    working =   working,
    slots =   slots.map { it.toDomain() }
)

/**
 * Перевод из AppointmentExpandDTO в AppointmentExpand
 */
internal fun AppointmentExpandDTO.toDomain()
= AppointmentExpand(
    pet = pet?.toDomain(),
    doctor = doctor?.toDomain() ,
    service = service?.toDomain(),
    clinic = clinic?.toDomain()
)

/**
 * Перевод из AppointmentDTO в Appointment
 */
internal fun AppointmentDTO.toDomain()
 = Appointment(
    id = id,
    number = number ,
    user = user,
    pet = pet,
    doctor = doctor,
    service = service,
    clinic = clinic,
    start = start,
    status = status,
    comment = comment,
    cancelReason = cancelReason,
    created = created,
    updated = updated,
    expand =  expand?.toDomain()
)

/**
 * Перевод из AppointmentListDTO в AppointmentList
 */
internal fun AppointmentListDTO.toDomain()  =
    AppointmentList(
        page = page,
        perPage = perPage,
        totalPages = totalPages,
        totalItems = totalItems,
        items = items.map { it.toDomain() }
    )
/**
 * Перевод из AppointmentUpdate в AppointmentUpdateDTO
 */
internal fun AppointmentUpdate.toRequest() =
    AppointmentUpdateDTO(
        pet = pet,
        doctor = doctor,
        service = service,
        start = start,
        comment = comment
    )

/**
 * Перевод из Cancel в CancelDTO
 */
internal fun Cancel.toRequest() =
    CancelDTO(
        reason = reason
    )