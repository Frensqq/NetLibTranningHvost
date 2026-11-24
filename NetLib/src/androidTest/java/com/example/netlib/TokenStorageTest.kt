package com.example.netlib

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.netlib.data.token.TokenStorageImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TokenStorageTest {
    @Test
    fun storedTokenSurvivesRecreationAndCanBeReplacedAndCleared() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val storage = TokenStorageImpl(context)
        try {
            storage.saveToken("first")
            assertEquals("first", TokenStorageImpl(context).getToken())
            storage.saveToken("second")
            assertEquals("second", TokenStorageImpl(context).getToken())
        } finally {
            storage.saveToken(null)
        }
        assertNull(TokenStorageImpl(context).getToken())
    }
}
