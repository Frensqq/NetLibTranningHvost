package com.example.netlib.data.repository

import com.example.netlib.data.mapper.toDomain
import com.example.netlib.data.remote.PBApi
import com.example.netlib.domain.model.ClinicList
import com.example.netlib.domain.model.DoctorList
import com.example.netlib.domain.model.NetworkResult
import com.example.netlib.domain.model.ServiceList
import com.example.netlib.domain.network.Network
import com.example.netlib.domain.repository.CatalogRepository


internal class CatalogRepositoryImpl(
    private val api: PBApi,
    network: Network
): PBRepositoryBase(network), CatalogRepository {

    override suspend fun getService(
        perPage: Int?,
        page: Int?,
        sort: String?
    ): NetworkResult<ServiceList> {

       return  safeApiCall { api.getService(
           perPage = perPage,
           page = page,
           sort = sort
       ).toDomain() }

    }

    override suspend fun getClinics(
        perPage: Int?,
        page: Int?,
        sort: String?
    ): NetworkResult<ClinicList> {
        return  safeApiCall {
            api.getClinics(
                perPage = perPage,
                page = page,
                sort = sort
                ).toDomain()
        }
    }

    override suspend fun getDoctors(
        perPage: Int?,
        page: Int?,
        sort: String?,
        filter: String?
    ): NetworkResult<DoctorList> {
        return  safeApiCall {
            api.getDoctors(
                perPage = perPage, page = page, sort = sort, filter = filter
            ).toDomain()
        }
    }


}