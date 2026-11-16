package com.example.netlib.domain.repository

import com.example.netlib.domain.model.ClinicList
import com.example.netlib.domain.model.DoctorList
import com.example.netlib.domain.model.NetworkResult
import com.example.netlib.domain.model.ServiceList


/**
 * CatalogRepository — определяет методы получения услуг, клиник и врачей
 */
interface CatalogRepository {

    suspend fun getService(
        perPage: Int? = null,
        page: Int? = null,
        sort: String? = null,
    ): NetworkResult<ServiceList>


    suspend fun getClinics(
        perPage: Int? = null,
        page: Int? = null,
        sort: String? = null,
    ): NetworkResult<ClinicList>

    suspend fun getDoctors(
        perPage: Int? = null,
        page: Int? = null,
        sort: String? = "-rating",
        filter: String? = null
    ): NetworkResult<DoctorList>
}