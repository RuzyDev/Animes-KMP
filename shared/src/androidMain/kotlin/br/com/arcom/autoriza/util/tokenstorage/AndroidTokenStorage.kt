package br.com.arcom.autoriza.util.tokenstorage

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class AndroidTokenStorage(context: Context) : TokenStorage {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString("auth_token", token).apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString("auth_token", null)
    }

    override fun clearToken() {
        sharedPreferences.edit().remove("auth_token").apply()
    }
}
