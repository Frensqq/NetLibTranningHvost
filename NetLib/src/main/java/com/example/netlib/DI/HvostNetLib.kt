package com.example.netlib.DI

import android.content.Context
import com.example.netlib.data.network.NetworkImpl
import com.example.netlib.data.remote.DTO.PetListDTO
import com.example.netlib.data.remote.PBApi
import com.example.netlib.data.remote.PBApiService
import com.example.netlib.data.repository.AppointmentsRepositoryImpl
import com.example.netlib.data.repository.AuthRepositoryImpl
import com.example.netlib.data.repository.CatalogRepositoryImpl
import com.example.netlib.data.repository.PetRepositoryImpl
import com.example.netlib.data.repository.ProfileRepositoryImpl
import com.example.netlib.data.token.TokenStorageImpl
import com.example.netlib.domain.repository.AppointmentRepository
import com.example.netlib.domain.repository.AuthRepository
import com.example.netlib.domain.repository.CatalogRepository
import com.example.netlib.domain.repository.PetRepository
import com.example.netlib.domain.repository.ProfileRepository
import com.example.netlib.domain.storage.TokenStorage
import io.ktor.client.HttpClient
import io.ktor.http.Url

class HvostNetLib(
    context: Context,
    baseUrl: String,
    private val tokenStorage: TokenStorage = TokenStorageImpl(context)
){

    private val clientFactory = PBApiService(
        defaultUrl = baseUrl,
        getToken = {tokenStorage.getToken()}
    )

    private val httpClient = clientFactory.create()

    private val api = PBApi(httpClient)

    private val networkMonitor = NetworkImpl(context.applicationContext)


    val authRepository: AuthRepository
    = AuthRepositoryImpl(
        api,
        tokenStorage,
        networkMonitor
    )

    val profileRepository: ProfileRepository =
        ProfileRepositoryImpl(api, networkMonitor)

    val catalogRepository: CatalogRepository =
        CatalogRepositoryImpl(api, networkMonitor)

    val petRepository: PetRepository =
        PetRepositoryImpl(api, networkMonitor)

    val appointmentRepository: AppointmentRepository =
        AppointmentsRepositoryImpl(api,networkMonitor)
}