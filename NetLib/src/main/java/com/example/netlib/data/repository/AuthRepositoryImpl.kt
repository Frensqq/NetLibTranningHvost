package com.example.netlib.data.repository

import com.example.netlib.data.mapper.toDomain
import com.example.netlib.data.mapper.toRequest
import com.example.netlib.data.remote.PBApi
import com.example.netlib.domain.model.AuthRequest
import com.example.netlib.domain.model.AuthResponse
import com.example.netlib.domain.model.ClinicList
import com.example.netlib.domain.model.DoctorList
import com.example.netlib.domain.model.NetworkResult
import com.example.netlib.domain.model.Registration
import com.example.netlib.domain.model.ServiceList
import com.example.netlib.domain.model.User
import com.example.netlib.domain.network.Network
import com.example.netlib.domain.repository.AuthRepository
import com.example.netlib.domain.repository.CatalogRepository
import com.example.netlib.domain.storage.TokenStorage

/**
 * AuthRepositoryImpl - реализация интерфейсов PBRepositoryBase AuthRepository
 *
 * @param api — экземпляр API-клиента
 * @param tokenStorage - хранилище токена
 * @param network - объект для проверки состояния интернет соединения
 *
 */
internal class AuthRepositoryImpl(
    private val api: PBApi,
    private val tokenStorage: TokenStorage,
    network: Network
): PBRepositoryBase(network), AuthRepository {
    override suspend fun register(data: Registration): NetworkResult<User> {
        return safeApiCall {
            api.register(data.toRequest()).toDomain()
        }
    }

    override suspend fun auth(data: AuthRequest): NetworkResult<AuthResponse> {
        return safeApiCall {
            val response = api.auth(data.toRequest()).toDomain()

            tokenStorage.saveToken(response.token)

            response
        }
    }

    override suspend fun authRefresh(): NetworkResult<AuthResponse> {
        return safeApiCall {
            val response = api.authRefresh().toDomain()

            tokenStorage.saveToken(response.token)

            response
        }
    }

    override fun saveToken(token: String?) {
        tokenStorage.saveToken(token)
    }

    override fun getToken(): String? {
        return  tokenStorage.getToken()
    }


}