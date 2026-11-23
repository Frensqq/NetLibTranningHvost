package com.example.netlib.domain.model

import kotlinx.serialization.Serializable

sealed interface WebSocketEvent

data class WebSocketUserDTO(
    val id: String,
    val email: String,
    val name: String,
): WebSocketEvent

data class HelloEventDTO(
    val user: WebSocketUserDTO,
    val serverTime: String
): WebSocketEvent

data class ErrorEventDTO(
    val code: String,
    val message: String?
): WebSocketEvent

data class PongEvent(
    val time: String
): WebSocketEvent

data class SubscribeEventDTO(
    val topic: String,
    val doctor: String,
    val date: String,
): WebSocketEvent

data class SlotTakenEventDTO(
    val doctor: String,
    val date: String,
    val time: String,
    val start: String
): WebSocketEvent

data class SlotFreesEventDTO(
    val doctor: String,
    val date: String,
    val time: String,
    val start: String
): WebSocketEvent


data class StatusEventDTO(
    val appointmentId: String,
    val number: Int,
    val status: String,
    val statusLabel: String,
    val previous: String,
    val cancerReason: String,
    val start: String,
    val changeAt: String
): WebSocketEvent


data class DelayEventDTO(
    val appointmentId: String,
    val number: Int,
    val minutes: Int,
    val newTime: String,
    val message: String,
): WebSocketEvent


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
): WebSocketEvent


data class QueueDemoStartedDTO(
    val appointmentId: String,
    val stepSec: Int
): WebSocketEvent

