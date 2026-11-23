package com.example.netlib.data.remote

import com.example.netlib.data.mapper.toDomain
import com.example.netlib.data.remote.DTO.websoket.DelayEventDTO
import com.example.netlib.data.remote.DTO.websoket.ErrorEventDTO
import com.example.netlib.data.remote.DTO.websoket.HelloEventDTO
import com.example.netlib.data.remote.DTO.websoket.PongEventDTO
import com.example.netlib.data.remote.DTO.websoket.QueueDemoStartedDTO
import com.example.netlib.data.remote.DTO.websoket.QueueEventDTO
import com.example.netlib.data.remote.DTO.websoket.SlotFreesEventDTO
import com.example.netlib.data.remote.DTO.websoket.SlotTakenEventDTO
import com.example.netlib.data.remote.DTO.websoket.StatusEventDTO
import com.example.netlib.data.remote.DTO.websoket.SubscribeEventDTO
import com.example.netlib.data.remote.DTO.websoket.UnsubscribeEventDTO
import com.example.netlib.data.remote.DTO.websoket.UnsubscribeEventOut
import com.example.netlib.domain.model.QueueDemoStarted
import com.example.netlib.domain.model.WebSocketEvent
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive

fun parge(message: String, json: Json): WebSocketEvent? {

    return try {


        val element = json.parseToJsonElement(message)
        val type = (element as? JsonObject)
            ?.get("type")
            ?.jsonPrimitive
            ?.content
            ?: return null

        when(type){
            "hello" -> json.decodeFromString<HelloEventDTO>(message).toDomain()
            "status" -> json.decodeFromString<StatusEventDTO>(message).toDomain()
            "error" -> json.decodeFromString<ErrorEventDTO>(message).toDomain()
            "pong" -> json.decodeFromString<PongEventDTO>(message).toDomain()
            "subscribed" -> json.decodeFromString<SubscribeEventDTO>(message).toDomain()
            "unsubscribed" -> json.decodeFromString<UnsubscribeEventDTO>(message).toDomain()
            "slot_taken" -> json.decodeFromString<SlotTakenEventDTO>(message).toDomain()
            "slot_freed" -> json.decodeFromString<SlotFreesEventDTO>(message).toDomain()
            "delay" -> json.decodeFromString<DelayEventDTO>(message).toDomain()
            "queue" -> json.decodeFromString<QueueEventDTO>(message).toDomain()
            "queue_demo_started" -> json.decodeFromString<QueueDemoStartedDTO>(message).toDomain()
            else -> null
        }
    } catch (e: Exception){
        null
    }
}