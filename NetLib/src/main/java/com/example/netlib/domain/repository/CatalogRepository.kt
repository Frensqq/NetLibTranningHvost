package com.example.netlib.domain.repository

import com.example.netlib.domain.model.ClinicList
import com.example.netlib.domain.model.DoctorList
import com.example.netlib.domain.model.NetworkResult
import com.example.netlib.domain.model.ServiceList


interface CatalogRepository {

    suspend fun getService(
        perPage: Int?,
        page: Int?,
        sort: String?
    ): NetworkResult<ServiceList>


    suspend fun getClinics(
        perPage: Int?,
        page: Int?,
        sort: String?
    ): NetworkResult<ClinicList>

    suspend fun getDoctors(
        perPage: Int?,
        page: Int?,
        sort: String? = "-rating",
        filter: String?
    ): NetworkResult<DoctorList>
}