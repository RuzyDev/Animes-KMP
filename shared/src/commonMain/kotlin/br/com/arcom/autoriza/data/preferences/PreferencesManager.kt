package br.com.arcom.autoriza.data.preferences

interface PreferencesManager {
    fun save(key: KeysPreferences, value: String)
    fun get(key: KeysPreferences): String?
    fun remove(key: KeysPreferences)
}

enum class KeysPreferences{
    ACCESS_TOKEN,
    REFRESH_TOKEN
}