package com.example.netlib.data.mapper

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
import com.example.netlib.data.remote.DTO.websoket.WebSocketUserDTO
import com.example.netlib.domain.model.DelayEvent
import com.example.netlib.domain.model.ErrorEvent
import com.example.netlib.domain.model.HelloEvent
import com.example.netlib.domain.model.PongEvent
import com.example.netlib.domain.model.QueueDemoStarted
import com.example.netlib.domain.model.QueueEvent
import com.example.netlib.domain.model.SlotFreesEvent
import com.example.netlib.domain.model.SlotTakenEvent
import com.example.netlib.domain.model.StatusEvent
import com.example.netlib.domain.model.SubscribeEvent
import com.example.netlib.domain.model.WebSocketUser


fun WebSocketUserDTO.toDomain() = WebSocketUser(
     id = id,
     email = email,
     name = name,
)

fun HelloEventDTO.toDomain() = HelloEvent(
     user = user.toDomain(),
     serverTime = serverTime
)

fun ErrorEventDTO.toDomain() = ErrorEvent(
    code = code,
    message  = message
)

fun PongEventDTO.toDomain() = PongEvent(
     time = time
)

fun SubscribeEventDTO.toDomain() = SubscribeEvent(
     topic = topic,
     doctor = doctor,
     date = date,
)

fun SlotTakenEventDTO.toDomain() = SlotTakenEvent(
    doctor = doctor,
    date = date,
    time = time,
    start = start
)

fun SlotFreesEventDTO.toDomain() = SlotFreesEvent(
     doctor = doctor,
     date = date,
     time =time,
     start = start
)


fun StatusEventDTO.toDomain() = StatusEvent(
     appointmentId = appointmentId,
     number = number,
     status  = status,
     statusLabel = statusLabel,
     previous = previous,
     cancerReason = cancerReason,
     start = start,
     changeAt = changeAt
)


fun DelayEventDTO.toDomain() = DelayEvent(
    appointmentId = appointmentId,
    number = number,
    minutes = minutes,
    newTime = newTime,
    message = message,
)


fun QueueEventDTO.toDomain() = QueueEvent(
    appointmentId = appointmentId,
    number = number,
    state = state,
    waiting = waiting,
    ahead = ahead,
    etaMin = etaMin,
    room = room,
    delayMin = delayMin,
    message = message,
)


fun QueueDemoStartedDTO.toDomain() = QueueDemoStarted(
    appointmentId = appointmentId,
    stepSec = stepSec
)
