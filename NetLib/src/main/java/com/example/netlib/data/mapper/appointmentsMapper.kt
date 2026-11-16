package com.example.netlib.data.mapper

import com.example.netlib.data.mapper.toDomain
import com.example.netlib.data.remote.DTO.AppointmentDTO
import com.example.netlib.data.remote.DTO.AppointmentExpandDTO
import com.example.netlib.data.remote.DTO.AppointmentListDTO
import com.example.netlib.data.remote.DTO.AppointmentUpdateDTO
import com.example.netlib.data.remote.DTO.CancelDTO
import com.example.netlib.data.remote.DTO.SlotDTO
import com.example.netlib.data.remote.DTO.SlotsDTO
import com.example.netlib.data.remote.PBApi
import com.example.netlib.data.repository.PBRepositoryBase
import com.example.netlib.domain.model.Appointment
import com.example.netlib.domain.model.AppointmentExpand
import com.example.netlib.domain.model.AppointmentList
import com.example.netlib.domain.model.AppointmentUpdate
import com.example.netlib.domain.model.Cancel
import com.example.netlib.domain.model.ClinicList
import com.example.netlib.domain.model.DoctorList
import com.example.netlib.domain.model.NetworkResult
import com.example.netlib.domain.model.ServiceList
import com.example.netlib.domain.model.Slot
import com.example.netlib.domain.model.Slots
import com.example.netlib.domain.network.Network
import com.example.netlib.domain.repository.AppointmentRepository
import com.example.netlib.domain.repository.CatalogRepository


internal fun SlotDTO.toDomain()
= Slot(
    time = time,
    start = start,
    free = free
)

internal fun SlotsDTO.toDomain()
= Slots(
    doctor =    doctor,
    date =  date,
    weekday =  weekday ,
    working =   working,
    slots =   slots.map { it.toDomain() }
)


internal fun AppointmentExpandDTO.toDomain()
= AppointmentExpand(
    pet = pet.toDomain(),
    doctor = doctor.toDomain() ,
    service = service.toDomain(),
    clinic = clinic.toDomain()
)


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
    expand =  expand.toDomain()
)


internal fun AppointmentListDTO.toDomain()  =
    AppointmentList(
        page = page,
        perPage = perPage,
        totalPages = totalPages,
        totalItems = totalItems,
        items = items.map { it.toDomain() }
    )

internal fun AppointmentUpdate.toRequest() =
    AppointmentUpdateDTO(
        pet = pet,
        doctor = doctor,
        service = service,
        start = start,
        comment = comment
    )

internal fun Cancel.toRequest() =
    CancelDTO(
        reason = reason
    )