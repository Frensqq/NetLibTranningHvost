package com.example.netlib.domain.repository

import com.example.netlib.domain.model.FileUpload
import com.example.netlib.domain.model.NetworkResult
import com.example.netlib.domain.model.Pet
import com.example.netlib.domain.model.PetList
import com.example.netlib.domain.model.PetUpdate

/**
 * PetRepository - интерфейс запросы для питомцев
 */
interface PetRepository {

    suspend fun getPet(
        perPage: Int?,
        page: Int?,
        sort: String?
    ): NetworkResult<PetList>

    suspend fun postPet(
        data: PetUpdate,
        photo: FileUpload? = null
    ): NetworkResult<Pet>


    suspend fun patchPet(
        id: String,
        data: PetUpdate,
        photo: FileUpload? = null
    ): NetworkResult<Pet>


    suspend fun deletePet(
        id: String
    ): NetworkResult<Unit>

}