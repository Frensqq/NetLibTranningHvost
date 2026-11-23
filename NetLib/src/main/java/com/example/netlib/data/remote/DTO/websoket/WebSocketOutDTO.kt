package com.example.netlib.data.remote.DTO.websoket

import kotlinx.serialization.Serializable

@Serializable
internal data class PingEvent(
    val type: String = "ping"
)

@Serializable
internal data class SubscribeEventOut(
    val type: String = "subscribe",
    val topic: String = "slots",
    val doctor: String ,
    val date: String,
)

@Serializable
internal data class UnsubscribeEventOut(
    val type: String = "unsubscribe",
    val topic: String = "slots",
    val doctor: String? ,
    val date: String?,
)


@Serializable
internal data class QueueDemoEventOut(
    val type: String = "queue_demo",
    val appointmentId: String = ""
)