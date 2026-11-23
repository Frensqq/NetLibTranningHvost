package com.example.netlib.data.remote.DTO.websoket

import io.ktor.websocket.CloseReason
import kotlinx.serialization.Serializable
@Serializable
data class WebSocketUserDTO(
    val id: String,
    val email: String,
    val name: String,
)
@Serializable
data class HelloEventDTO(
    val user: WebSocketUserDTO,
    val serverTime: String
)
@Serializable
data class ErrorEventDTO(
    val code: String,
    val message: String?
)
@Serializable
data class PongEventDTO (
    val time: String
)
@Serializable
data class SubscribeEventDTO(
    val topic: String,
    val doctor: String,
    val date: String,
)
@Serializable
data class SlotTakenEventDTO(
    val doctor: String,
    val date: String,
    val time: String,
    val start: String
)
@Serializable
data class SlotFreesEventDTO(
    val doctor: String,
    val date: String,
    val time: String,
    val start: String
)

@Serializable
data class StatusEventDTO(
    val appointmentId: String,
    val number: Int,
    val status: String,
    val statusLabel: String,
    val previous: String,
    val cancerReason: String,
    val start: String,
    val changeAt: String
)

@Serializable
data class DelayEventDTO(
    val appointmentId: String,
    val number: Int,
    val minutes: Int,
    val newTime: String,
    val message: String,
)

@Serializable
data class QueueEventDTO(
    val appointmentId: String,
    val number: Int,
    val state: String,
    val waiting: String,
    val ahead: Int,
    val etaMin: Int,
    val room: String,
    val delayMin: Int,
    val message: String,
)

@Serializable
data class QueueDemoStartedDTO(
    val appointmentId: String,
    val stepSec: Int
)
