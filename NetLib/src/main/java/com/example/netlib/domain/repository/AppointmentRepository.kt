package com.example.netlib.domain.repository


import com.example.netlib.domain.model.Appointment
import com.example.netlib.domain.model.AppointmentList
import com.example.netlib.domain.model.AppointmentUpdate
import com.example.netlib.domain.model.Cancel
import com.example.netlib.domain.model.NetworkResult
import com.example.netlib.domain.model.Slots

interface AppointmentRepository {

    suspend fun getSlots(doctorId: String, date: String): NetworkResult<Slots>

    suspend fun getAppointments(
        filter: String?,
        sort: String?,
        page: Int?,
        perPage: Int?
    ): NetworkResult<AppointmentList>

    suspend fun postAppointments(
        data: AppointmentUpdate
    ): NetworkResult<Appointment>



    suspend fun canselAppointment(
        id: String,
        data: Cancel,
    ): NetworkResult<Appointment>

}