package br.com.arcom.autoriza.android

import android.app.Application
import androidx.lifecycle.viewmodel.viewModelFactory
import br.com.arcom.autoriza.di.initKoin
import br.com.arcom.autoriza.domain.observers.ObserveDetalhesSolicitacao
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.compose.get
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger(level = Level.NONE)
            androidContext(androidContext = this@MainApplication)

        }
    }
}
