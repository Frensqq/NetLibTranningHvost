package com.example.netlib.data.repository

import com.example.netlib.data.mapper.toDomain
import com.example.netlib.data.mapper.toRequest
import com.example.netlib.data.remote.PBApi
import com.example.netlib.domain.model.FileUpload
import com.example.netlib.domain.model.NetworkResult
import com.example.netlib.domain.model.Pet
import com.example.netlib.domain.model.PetList
import com.example.netlib.domain.model.PetUpdate
import com.example.netlib.domain.network.Network
import com.example.netlib.domain.repository.PetRepository


internal class PetRepositoryImpl(
    private val api: PBApi,
    network: Network
): PBRepositoryBase(network), PetRepository {
    override suspend fun getPet(
        perPage: Int?,
        page: Int?,
        sort: String?
    ): NetworkResult<PetList> {
       return safeApiCall {
           api.getPet(
               perPage = perPage,
               page = page,
               sort = sort
           ).toDomain()
       }
    }

    override suspend fun postPet(
        data: PetUpdate,
        photo: FileUpload?
    ): NetworkResult<Pet> {
       return safeApiCall {
           api.postPet(
               data = data.toRequest(),
               photo = photo?.toRequest()
           ).toDomain()
       }
    }

    override suspend fun patchPet(
        id: String,
        data: PetUpdate,
        photo: FileUpload?
    ): NetworkResult<Pet> {
       return safeApiCall {
           api.patchPet(id, data.toRequest(), photo?.toRequest()).toDomain()
       }
    }

    override suspend fun deletePet(id: String): NetworkResult<Unit> {
       return safeApiCall {
           api.deletePet(id)
       }
    }

}