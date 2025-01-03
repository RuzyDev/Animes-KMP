package br.com.arcom.apparcom.di

import app.cash.sqldelight.db.SqlDriver
import br.com.arcom.apparcom.data.datastore.AppArcomStorage
import br.com.arcom.apparcom.db.SqlDelightDriverFactory
import br.com.arcom.apparcom.data.datastore.createDataStore
import br.com.arcom.apparcom.data.preferences.AndroidPreferencesManager
import br.com.arcom.apparcom.data.preferences.PreferencesManager
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> { SqlDelightDriverFactory(context = get()).createDriver() }
    single<HttpClientEngine> { OkHttp.create {} }
    single<AppArcomStorage> { AppArcomStorage(createDataStore(context = get())) }
    single<PreferencesManager> { AndroidPreferencesManager(context = get()) }
}
