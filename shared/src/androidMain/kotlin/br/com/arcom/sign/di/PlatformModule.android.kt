package br.com.arcom.sign.di

import app.cash.sqldelight.db.SqlDriver
import br.com.arcom.sign.db.SqlDelightDriverFactory
import br.com.arcom.sign.util.dataStore
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        SqlDelightDriverFactory(context = get()).createDriver()
    }

    single<HttpClientEngine> {
        OkHttp.create {}
    }

    single { dataStore(get())}
}
