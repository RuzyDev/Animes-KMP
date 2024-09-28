package br.com.arcom.sign.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import br.com.arcom.sign.util.datastore.createDataStore
import br.com.arcom.sign.util.datastore.dataStoreFileName


fun dataStore(): DataStore<Preferences> = createDataStore(
    producePath = {
//        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
//            directory = NSDocumentDirectory,
//            inDomain = NSUserDomainMask,
//            appropriateForURL = null,
//            create = false,
//            error = null,
//        )
//        requireNotNull(documentDirectory).path +
                "/$dataStoreFileName"
    }
)