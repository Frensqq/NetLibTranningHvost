package com.example.netlib.data.repository

import com.example.netlib.data.mapper.toDomain
import com.example.netlib.data.mapper.toRequest
import com.example.netlib.data.remote.PBApi
import com.example.netlib.domain.model.FileUpload
import com.example.netlib.domain.model.NetworkResult
import com.example.netlib.domain.model.PatchUser
import com.example.netlib.domain.model.User
import com.example.netlib.domain.network.Network
import com.example.netlib.domain.repository.CatalogRepository
import com.example.netlib.domain.repository.ProfileRepository

/**
 * ProfileRepositoryImpl - реализация интерфейсов PBRepositoryBase ProfileRepository
 *
 * @param api — экземпляр API-клиента
 * @param network - объект для проверки состояния интернет соединения
 *
 */
internal class ProfileRepositoryImpl(
    private val api: PBApi,
    network: Network
): PBRepositoryBase(network), ProfileRepository {
    override suspend fun getProfile(userId: String): NetworkResult<User> {
        return safeApiCall {
            api.getProfile(userId).toDomain()
        }
    }

    override suspend fun patchProfile(
        userId: String,
        data: PatchUser,
        avatar: FileUpload?
    ): NetworkResult<User> {
        return safeApiCall {
            api.patchProfile(userId, data.toRequest(), avatar?.toRequest()).toDomain()
        }
    }


}