package com.example.netlib.data.repository

import com.example.netlib.data.mapper.toDomain
import com.example.netlib.data.mapper.toRequest
import com.example.netlib.data.remote.PBApi
import com.example.netlib.domain.model.Appointment
import com.example.netlib.domain.model.AppointmentList
import com.example.netlib.domain.model.AppointmentUpdate
import com.example.netlib.domain.model.Cancel
import com.example.netlib.domain.model.ClinicList
import com.example.netlib.domain.model.DoctorList
import com.example.netlib.domain.model.NetworkResult
import com.example.netlib.domain.model.ServiceList
import com.example.netlib.domain.model.Slots
import com.example.netlib.domain.network.Network
import com.example.netlib.domain.repository.AppointmentRepository
import com.example.netlib.domain.repository.CatalogRepository


internal class AppointmentsRepositoryImpl(
    private val api: PBApi,
    network: Network
): PBRepositoryBase(network), AppointmentRepository {
    override suspend fun getSlots(doctorId: String, date: String): NetworkResult<Slots> {
        return safeApiCall {
            api.getSlots(doctorId, date).toDomain()
        }
    }

    override suspend fun getAppointments(
        filter: String?,
        sort: String?,
        page: Int?,
        perPage: Int?
    ): NetworkResult<AppointmentList> {
        return safeApiCall {
            api.getAppointments(
                filter, sort, page, perPage
            ).toDomain()
        }
    }

    override suspend fun postAppointments(data: AppointmentUpdate): NetworkResult<Appointment> {
        return safeApiCall {
            api.postAppointments(data.toRequest()).toDomain()
        }
    }

    override suspend fun canselAppointment(
        id: String,
        data: Cancel
    ): NetworkResult<Appointment> {
        return safeApiCall {
            api.canselAppointment(id, data.toRequest()).toDomain()
        }
    }
}