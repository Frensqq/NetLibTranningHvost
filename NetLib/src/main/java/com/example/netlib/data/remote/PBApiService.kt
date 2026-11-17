package com.example.netlib.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


/**
 * PBApiService - настройка Http клиента для библиотеки
 *
 * @param defaultUrl - путь к серверу
 * @param getToken - токен авторизации
 *
 */
internal class PBApiService(
    private val defaultUrl: String,
    private val getToken: () -> String?
){

    private val json = Json{
        encodeDefaults = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    fun create(): HttpClient{
        return HttpClient(OkHttp){
            expectSuccess = true


            install(ContentNegotiation){
             json(json)
            }

            install(Logging){
                level = LogLevel.HEADERS


                    sanitizeHeader { header ->
                        header == HttpHeaders.Authorization
                    }

            }

            install(HttpTimeout){
                connectTimeoutMillis = 30000
            }

            defaultRequest {
                defaultUrl
                getToken()?.let {
                    header(HttpHeaders.Authorization, it)
                }
            }
        }
    }
}