package br.com.arcom.autoriza.util.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath

fun createDataStore(
    producePath: () -> String,
): DataStore<Preferences> = PreferenceDataStoreFactory.createWithPath(
    corruptionHandler = null,
    migrations = emptyList(),
    produceFile = { producePath().toPath() },
)

internal const val dataStoreFileName = "autoriza.preferences_pb"

class AnimesHubDataStore(
    private val dataStore: DataStore<Preferences>
) {

    val teste = MutableStateFlow(0)

    fun getStringStream(key: Keys): Flow<String> {
        return dataStore.data
            .map { preferences ->
                preferences[stringPreferencesKey(key.key)] ?: ""
            }
    }

    fun getLongStream(key: Keys): Flow<Long> {
        return dataStore.data
            .map { preferences ->
                preferences[longPreferencesKey(key.key)] ?: 0L
            }
    }

    fun getBooleanStream(key: Keys): Flow<Boolean> {
        return dataStore.data
            .map { preferences ->
                preferences[booleanPreferencesKey(key.key)] ?: false
            }
    }

    fun getListStringStream(key: Keys): Flow<List<String>> {
        return dataStore.data
            .map { preferences ->
                (preferences[stringPreferencesKey(key.key)] ?: "").split(";")
            }
    }


    suspend fun emitString(value: String, key: Keys) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key.key)] = value
        }
    }

    suspend fun emitLong(value: Long, key: Keys) {
        dataStore.edit { preferences ->
            preferences[longPreferencesKey(key.key)] = value
        }
    }

    suspend fun emitBoolean(value: Boolean, key: Keys) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key.key)] = value
        }
    }

    suspend fun emitListString(value: List<String>, key: Keys) {
        val list = value.joinToString(";")
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key.key)] = list
        }
    }

    suspend fun clearBoolean(key: Keys) {
        dataStore.edit { preferences ->
            preferences.remove(booleanPreferencesKey(key.key))
        }
    }

    suspend fun clearString(key: Keys) {
        dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(key.key))
        }
    }

    suspend fun clearLong(key: Keys) {
        dataStore.edit { preferences ->
            preferences.remove(longPreferencesKey(key.key))
        }
    }
}

enum class Keys(val key: String) {
    ULTIMA_VERSAO("ultima_versao"),
    PRIMEIRO_ACESSO("primeiro_acesso"),
    TOKEN_ARCOM_ID("token_arcom_id"),
    TOKEN_BEARER("token_bearer"),
    DADOS_USUARIO("dados_usuario");
}
