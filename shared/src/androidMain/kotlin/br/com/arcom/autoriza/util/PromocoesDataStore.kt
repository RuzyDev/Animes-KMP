package br.com.arcom.autoriza.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import br.com.arcom.autoriza.util.datastore.createDataStore
import br.com.arcom.autoriza.util.datastore.dataStoreFileName


fun dataStore(context: Context): DataStore<Preferences> =
    createDataStore(
        producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
    )