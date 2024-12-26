package br.com.arcom.autoriza.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.db.SqlDriver
import br.com.arcom.autoriza.data.datastore.AppArcomStorage
import br.com.arcom.autoriza.db.SqlDelightDriverFactory
import br.com.arcom.autoriza.data.datastore.createDataStore
import br.com.arcom.autoriza.data.preferences.AndroidPreferencesManager
import br.com.arcom.autoriza.data.preferences.PreferencesManager
import br.com.arcom.autoriza.presentation.MainActivityViewModel
import br.com.arcom.autoriza.presentation.PerfilViewModel
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> { SqlDelightDriverFactory(context = get()).createDriver() }
    single<HttpClientEngine> { OkHttp.create {} }
    single<AppArcomStorage> { AppArcomStorage(createDataStore(context = get())) }
    single<PreferencesManager> { AndroidPreferencesManager(context = get()) }

    factory { MainActivityViewModel() }
}
