package com.example.netlib.data.remote.DTO.websoket

import io.ktor.websocket.CloseReason
import kotlinx.serialization.Serializable
/**
 * Модель User дял websocket
 */
@Serializable
internal data class WebSocketUserDTO(
    val id: String,
    val email: String,
    val name: String,
)

/**
 * HelloEventDTO - модель запроса на подключение
 */
@Serializable
internal data class HelloEventDTO(
    val user: WebSocketUserDTO,
    val serverTime: String
)

/**
 * HelloEventDTO - Модель ошибки
 */
@Serializable
internal data class ErrorEventDTO(
    val code: String,
    val message: String?
)
/**
 * PongEventDTO - Модель ответ сервера на запрос ping
 */
@Serializable
internal data class PongEventDTO (
    val time: String = ""
)
/**
 *  SubscribeEventDTO - Модель для запроса на подписку
 */
@Serializable
internal data class SubscribeEventDTO(
    val topic: String,
    val doctor: String,
    val date: String,
)
/**
 *  SubscribeEventDTO - Модель для запроса на отписку
 */
@Serializable
internal data class UnsubscribeEventDTO(
    val topic: String,
    val doctor: String? = null,
    val date: String? = null,
)

/**
 * Модель SlotTakenEventDTO - Ответ событие по подписке, кто-то записался на это время
 */
@Serializable
internal data class SlotTakenEventDTO(
    val doctor: String,
    val date: String,
    val time: String,
    val start: String
)
/**
 * Модель SlotFreesEventDTO - Ответ событие по подписке, запись отменили
 */
@Serializable
internal data class SlotFreesEventDTO(
    val doctor: String,
    val date: String,
    val time: String,
    val start: String
)

/**
 * Модель StatusEventDTO - Ответ на события по записям  Смена статуса (подтверждение, отмена вами или клиникой, завершение)
 */
@Serializable
internal data class StatusEventDTO(
    val appointmentId: String,
    val number: Int,
    val status: String,
    val statusLabel: String,
    val previous: String,
    val cancelReason: String,
    val start: String,
    val changedAt: String
)

/**
 * Модель DelayEventDTO - Ответ на события по записям  Врач задерживается
 */
@Serializable
internal data class DelayEventDTO(
    val appointmentId: String,
    val number: Int,
    val minutes: Int,
    val newTime: String,
    val message: String,
)

/**
 * QueueEventDTO - Живая очередь
 */
@Serializable
internal data class QueueEventDTO(
    val appointmentId: String,
    val number: Int,
    val state: String,
    val ahead: Int,
    val etaMin: Int,
    val room: String,
    val delayMin: Int,
    val message: String,
)

/**
 * QueueEventDTO - Модель демонcтрации очереди
 */
@Serializable
internal data class QueueDemoStartedDTO(
    val appointmentId: String,
    val stepSec: Int
)
