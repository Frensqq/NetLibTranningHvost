package com.example.netlib.data.remote

import com.example.netlib.domain.websocket.WSConnection
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.client.request.url
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText

/**
 *
 *
 */
internal class WSConnectionImpl(
    private val httpClient: HttpClient,
    private val wsUrl: String
): WSConnection {
    @Volatile
    var session: DefaultWebSocketSession? = null

    override suspend fun connection(token: String) {
        disconnected()

        session = httpClient.webSocketSession {
            url(wsUrl)
            header(HttpHeaders.Authorization, token)
        }

    }

    override suspend fun disconnected() {
        val currentSession = session
        session = null
        currentSession?.close()
    }

    override suspend fun send(message: String) {
        val currentSession = session
            ?: throw IllegalArgumentException("Web socket not connected")

        currentSession.send(Frame.Text(message))
    }

    suspend fun receive(): String? {
        val currentSession = session
            ?: throw IllegalStateException("WebSocket is not connected")

        return when (val frame = currentSession.incoming.receive()) {
            is Frame.Text -> frame.readText()
            else -> null
        }
    }

    override suspend fun listen(onMessage: suspend (String) -> Unit): Int? {
        val currentSession = session
            ?: throw IllegalStateException("WebSocket is not connected")

        for (frame in currentSession.incoming){
            when(frame){
                is Frame.Text -> onMessage(frame.readText())
                is Frame.Close -> return currentSession.closeReason.await()?.code?.toInt()
                else -> Unit
            }
        }
        return currentSession.closeReason.await()?.code?.toInt()
    }
}