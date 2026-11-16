package com.example.netlib.data.token

import android.content.Context
import com.example.netlib.domain.storage.TokenStorage

internal class TokenStorageImpl(
    private val context: Context
): TokenStorage {

    val preferences = context.getSharedPreferences("net_lib_hvost", Context.MODE_PRIVATE)

    override fun getToken(): String? {
        return preferences.getString("token_auth", null)
    }

    override fun saveToken(token: String?) {
        preferences.edit()?.apply {
            if (token == null) remove("token_auth")
            else putString("token_auth", token)
        }

    }


}