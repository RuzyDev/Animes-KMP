package br.com.arcom.autoriza.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import br.com.arcom.autoriza.util.tokenstorage.createDataStore
import br.com.arcom.autoriza.util.tokenstorage.dataStoreFileName


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