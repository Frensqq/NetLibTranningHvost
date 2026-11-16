package com.example.netlib.domain.network


/**
 * Network - определяет методы проверки интернет соединения
 *
 */
interface Network {
    fun isConnected(): Boolean
}