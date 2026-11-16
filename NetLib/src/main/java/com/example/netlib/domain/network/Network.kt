package com.example.netlib.domain.network


/**
 * Network - интерфейс для проверки интернета
 */
interface Network {
    fun isConnected(): Boolean
}