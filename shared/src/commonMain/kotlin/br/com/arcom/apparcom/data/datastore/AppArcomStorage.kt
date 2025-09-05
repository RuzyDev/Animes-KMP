package br.com.arcom.apparcom.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppAnimeStorage(
    private val dataStore: DataStore<Preferences>
) {
    fun getStringStream(key: Keys): Flow<String> {
        return dataStore.data
            .map { preferences ->
                preferences[stringPreferencesKey(key.name)] ?: ""
            }
    }

    fun getLongStream(key: Keys): Flow<Long> {
        return dataStore.data
            .map { preferences ->
                preferences[longPreferencesKey(key.name)] ?: 0L
            }
    }

    fun getBooleanStream(key: Keys): Flow<Boolean> {
        return dataStore.data
            .map { preferences ->
                preferences[booleanPreferencesKey(key.name)] ?: false
            }
    }

    fun getListStringStream(key: Keys): Flow<List<String>> {
        return dataStore.data
            .map { preferences ->
                (preferences[stringPreferencesKey(key.name)] ?: "").split(";")
            }
    }

    suspend fun emitString(value: String, key: Keys) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key.name)] = value
        }
    }

    suspend fun emitLong(value: Long, key: Keys) {
        dataStore.edit { preferences ->
            preferences[longPreferencesKey(key.name)] = value
        }
    }

    suspend fun emitBoolean(value: Boolean, key: Keys) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key.name)] = value
        }
    }

    suspend fun emitListString(value: List<String>, key: Keys) {
        val list = value.joinToString(";")
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key.name)] = list
        }
    }

    suspend fun clearBoolean(key: Keys) {
        dataStore.edit { preferences ->
            preferences.remove(booleanPreferencesKey(key.name))
        }
    }

    suspend fun clearString(key: Keys) {
        dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(key.name))
        }
    }

    suspend fun clearLong(key: Keys) {
        dataStore.edit { preferences ->
            preferences.remove(longPreferencesKey(key.name))
        }
    }
}

enum class Keys {
    LOGADO,
    VERSAO_APP
}