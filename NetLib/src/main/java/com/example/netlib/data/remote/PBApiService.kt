package com.example.netlib.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class PBApiService(
    private val defaultUrl: String,
    private val getToken: () -> String?
) {
    val json = Json { ignoreUnknownKeys = true; encodeDefaults = true }

    fun create(): HttpClient = HttpClient(OkHttp) { configureClient() }

    fun create(engine: HttpClientEngine): HttpClient = HttpClient(engine) { configureClient() }

    private fun HttpClientConfig<*>.configureClient() {
        expectSuccess = true
        install(Logging) {
            level = LogLevel.HEADERS
            sanitizeHeader { it.equals(HttpHeaders.Authorization, ignoreCase = true) }
        }
        install(ContentNegotiation) { json(json) }
        install(WebSockets)
        install(HttpTimeout) {
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 30_000
        }
        defaultRequest {
            url(defaultUrl.trimEnd('/') + "/")
            if (!headers.contains(HttpHeaders.Authorization)) {
                getToken()?.let { header(HttpHeaders.Authorization, it) }
            }
        }
    }
}
