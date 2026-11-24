package com.example.netlib.data.token

import android.content.Context
import com.example.netlib.domain.storage.TokenStorage

/**
 * TokenStorageImpl - реализация интерфейса TokenStorage
 *  хранение и управление токеном
 *
 *  @param context - контекст android
 */
internal class TokenStorageImpl(
    context: Context
): TokenStorage {

    private val preferences = context.applicationContext.getSharedPreferences("net_lib_hvost", Context.MODE_PRIVATE)

    private companion object { const val TOKEN_KEY = "auth_token" }

    override fun getToken(): String? {
        return preferences.getString(TOKEN_KEY, null)
    }

    override fun saveToken(token: String?) {
        preferences.edit().apply {
            if (token == null) {
                remove(TOKEN_KEY)
            } else {
                putString(TOKEN_KEY, token)
            }
            apply()
        }

    }


}