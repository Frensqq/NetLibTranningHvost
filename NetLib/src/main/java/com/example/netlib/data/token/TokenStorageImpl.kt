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
    private val context: Context
): TokenStorage {

    val preferences = context.getSharedPreferences("net_lib_hvost", Context.MODE_PRIVATE)

    override fun getToken(): String? {
        return preferences.getString("token_auth", null)
    }

    override fun saveToken(token: String?) {
        preferences.edit().apply {
            if (token == null) {
                remove("auth_token")
            } else {
                putString("auth_token", token)
            }
            apply()
        }

    }


}