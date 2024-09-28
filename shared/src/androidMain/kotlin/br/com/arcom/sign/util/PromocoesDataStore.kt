package br.com.arcom.sign.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import br.com.arcom.sign.util.datastore.createDataStore
import br.com.arcom.sign.util.datastore.dataStoreFileName


fun dataStore(context: Context): DataStore<Preferences> =
    createDataStore(
        producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
    )