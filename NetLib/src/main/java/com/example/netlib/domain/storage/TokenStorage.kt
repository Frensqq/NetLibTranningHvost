package com.example.netlib.domain.storage

/**
 * Интерфейс хранение токена
 */
interface TokenStorage {

    fun getToken(): String?

    fun saveToken(token: String?)
}