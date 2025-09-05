package br.com.arcom.apparcom.di

import app.cash.sqldelight.db.SqlDriver
import br.com.arcom.apparcom.data.AndroidAppAnimeFiles
import br.com.arcom.apparcom.data.datastore.AppAnimeStorage
import br.com.arcom.apparcom.data.datastore.createDataStore
import br.com.arcom.apparcom.data.preferences.AndroidPreferencesManager
import br.com.arcom.apparcom.data.preferences.PreferencesManager
import br.com.arcom.apparcom.db.SqlDelightDriverFactory
import br.com.arcom.apparcom.presentation.AppViewModel
import br.com.arcom.apparcom.presentation.DetalhesSolicitacaoViewModel
import br.com.arcom.apparcom.presentation.HomeViewModel
import br.com.arcom.apparcom.presentation.LoginViewModel
import br.com.arcom.apparcom.presentation.SolicitacoesViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> { SqlDelightDriverFactory(context = get()).createDriver() }
    single{ AndroidAppAnimeFiles(context = get()) }
    single<HttpClientEngine> { OkHttp.create {} }
    single<AppAnimeStorage> { AppAnimeStorage(createDataStore(context = get())) }
    single<PreferencesManager> { AndroidPreferencesManager(context = get()) }
}
