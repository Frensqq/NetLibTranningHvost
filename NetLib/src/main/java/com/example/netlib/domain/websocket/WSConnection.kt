package com.example.netlib.domain.websocket

import android.os.Message
import io.ktor.client.plugins.api.Send

interface WSConnection {

    suspend fun connection(token: String)

    suspend fun disconnected()

    suspend fun send(message: String)

    suspend fun listen(onMessage: suspend (String) -> Unit): Int?

}