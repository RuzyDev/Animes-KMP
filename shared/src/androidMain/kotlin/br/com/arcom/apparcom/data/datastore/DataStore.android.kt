package br.com.arcom.apparcom.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

fun createDataStore(context: Context): DataStore<Preferences> =
    br.com.arcom.apparcom.data.datastore.createDataStore(
        producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
    )