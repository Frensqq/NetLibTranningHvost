package com.example.netlib.domain.storage

/**
 * TokenStorage — определяет методы сохранения и получения токена.
 *
 */
interface TokenStorage {

    fun getToken(): String?

    fun saveToken(token: String?)

}