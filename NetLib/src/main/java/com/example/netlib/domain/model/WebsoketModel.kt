package com.example.netlib.domain.model


sealed interface WebSocketEvent

data class WebSocketUser(
    val id: String,
    val email: String,
    val name: String,
): WebSocketEvent

data class HelloEvent(
    val user: WebSocketUser,
    val serverTime: String
): WebSocketEvent

data class ErrorEvent(
    val code: String,
    val message: String?
): WebSocketEvent

data class PongEvent(
    val time: String
): WebSocketEvent

data class SubscribeEvent(
    val topic: String,
    val doctor: String,
    val date: String,
): WebSocketEvent
data class UnsubscribeEvent(
    val topic: String,
    val doctor: String? =null,
    val date: String? = null,
): WebSocketEvent

data class SlotTakenEvent(
    val doctor: String,
    val date: String,
    val time: String,
    val start: String
): WebSocketEvent

data class SlotFreesEvent(
    val doctor: String,
    val date: String,
    val time: String,
    val start: String
): WebSocketEvent


data class StatusEvent(
    val appointmentId: String,
    val number: Int,
    val status: String,
    val statusLabel: String,
    val previous: String,
    val cancelReason: String,
    val start: String,
    val changedAt: String
): WebSocketEvent


data class DelayEvent(
    val appointmentId: String,
    val number: Int,
    val minutes: Int,
    val newTime: String,
    val message: String,
): WebSocketEvent


data class QueueEvent(
    val appointmentId: String,
    val number: Int,
    val state: String,
    val ahead: Int,
    val etaMin: Int,
    val room: String,
    val delayMin: Int,
    val message: String,
): WebSocketEvent


data class QueueDemoStarted(
    val appointmentId: String,
    val stepSec: Int
): WebSocketEvent

