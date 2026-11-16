package com.example.netlib.domain.storage

interface TokenStorage {

    fun getToken(): String?

    fun saveToken(token: String?)
}