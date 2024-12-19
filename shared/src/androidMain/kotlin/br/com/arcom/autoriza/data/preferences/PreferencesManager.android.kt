package br.com.arcom.autoriza.data.preferences

import android.content.Context
import android.content.SharedPreferences

class AndroidPreferencesManager(context: Context) : PreferencesManager {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    override fun save(key: KeysPreferences, value: String) {
        sharedPreferences.edit().putString(key.name, value).apply()
    }

    override fun get(key: KeysPreferences): String? {
        return sharedPreferences.getString(key.name, null)
    }

    override fun remove(key: KeysPreferences) {
        sharedPreferences.edit().remove(key.name).apply()
    }
}