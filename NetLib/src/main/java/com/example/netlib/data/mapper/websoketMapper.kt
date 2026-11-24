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
import com.example.netlib.data.remote.DTO.websoket.UnsubscribeEventDTO
import com.example.netlib.data.remote.DTO.websoket.UnsubscribeEventOut
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
import com.example.netlib.domain.model.UnsubscribeEvent
import com.example.netlib.domain.model.WebSocketUser

/**
 * Преобразование WebSocketUserDTO в WebSocketUser
 */
internal fun WebSocketUserDTO.toDomain() = WebSocketUser(
     id = id,
     email = email,
     name = name,
)


/**
 * Преобразование HelloEventDTO в HelloEvent
 */
internal fun HelloEventDTO.toDomain() = HelloEvent(
     user = user.toDomain(),
     serverTime = serverTime
)

/**
 * Преобразование ErrorEventDTO в ErrorEvent
 */
internal fun ErrorEventDTO.toDomain() = ErrorEvent(
    code = code,
    message  = message
)

/**
 * Преобразование PongEventDTO в PongEvent
 */
internal fun PongEventDTO.toDomain() = PongEvent(
     time = time
)

/**
 * Преобразование SubscribeEventDTO в SubscribeEvent
 */
internal fun SubscribeEventDTO.toDomain() = SubscribeEvent(
     topic = topic,
     doctor = doctor,
     date = date,
)

/**
 * Преобразование SlotTakenEventDTO в SlotTakenEvent
 */
internal fun SlotTakenEventDTO.toDomain() = SlotTakenEvent(
    doctor = doctor,
    date = date,
    time = time,
    start = start
)

/**
 * Преобразование SlotFreesEventDTO в SlotFreesEvent
 */
internal fun SlotFreesEventDTO.toDomain() = SlotFreesEvent(
     doctor = doctor,
     date = date,
     time =time,
     start = start
)

/**
 * Преобразование StatusEventDTO в StatusEvent
 */
internal fun StatusEventDTO.toDomain() = StatusEvent(
     appointmentId = appointmentId,
     number = number,
     status  = status,
     statusLabel = statusLabel,
     previous = previous,
     cancelReason = cancelReason,
     start = start,
     changedAt = changedAt
)

/**
 * Преобразование DelayEventDTO в DelayEvent
 */
internal fun DelayEventDTO.toDomain() = DelayEvent(
    appointmentId = appointmentId,
    number = number,
    minutes = minutes,
    newTime = newTime,
    message = message,
)

/**
 * Преобразование QueueEventDTO в QueueEvent
 */
internal fun QueueEventDTO.toDomain() = QueueEvent(
    appointmentId = appointmentId,
    number = number,
    state = state,
    ahead = ahead,
    etaMin = etaMin,
    room = room,
    delayMin = delayMin,
    message = message,
)
/**
 * Преобразование UnsubscribeEventDTO в UnsubscribeEvent
 */
internal fun UnsubscribeEventDTO.toDomain() = UnsubscribeEvent(
    topic = topic,
    doctor = doctor,
    date = date
)

/**
 * Преобразование QueueDemoStartedDTO в QueueDemoStarted
 */
internal fun QueueDemoStartedDTO.toDomain() = QueueDemoStarted(
    appointmentId = appointmentId,
    stepSec = stepSec
)
