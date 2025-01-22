package br.com.arcom.apparcom.android

import android.app.Application
import br.com.arcom.apparcom.android.core.service.AndroidTokenService
import br.com.arcom.apparcom.di.initKoin
import br.com.arcom.apparcom.service.TokenService
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.dsl.module

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(
            additionalModule = module {
                single<FirebaseCrashlytics> { FirebaseCrashlytics.getInstance() }
                single<FirebaseAnalytics> { FirebaseAnalytics.getInstance(this@MainApplication) }
                single<FirebaseMessaging> { FirebaseMessaging.getInstance() }
                single<TokenService> { AndroidTokenService(get()) }
            }
        ) {
            androidLogger(level = Level.NONE)
            androidContext(androidContext = this@MainApplication)
        }
    }
}
