package com.example.netlib.data.remote.DTO.websoket

import kotlinx.serialization.Serializable

@Serializable
data class PingEvent(
    val type: String = "ping"
)

@Serializable
data class SubscribeEventOut(
    val type: String = "subscribe",
    val topic: String = "slots",
    val doctor: String ,
    val date: String,
)

@Serializable
data class UnsubscribeEventOut(
    val type: String = "unsubscribe",
    val topic: String = "slots",
    val doctor: String? ,
    val date: String?,
)


@Serializable
data class QueueDomeEventOut(
    val type: String = "queue_demo",
    val appointmentId: String = ""
)