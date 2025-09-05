package br.com.arcom.apparcom.android.di

import android.content.Context
import br.com.arcom.apparcom.android.core.service.AndroidTokenService
import br.com.arcom.apparcom.android.utils.buildImageLoader
import br.com.arcom.apparcom.presentation.AppViewModel
import br.com.arcom.apparcom.presentation.DetalhesSolicitacaoViewModel
import br.com.arcom.apparcom.presentation.HomeViewModel
import br.com.arcom.apparcom.presentation.LoginViewModel
import br.com.arcom.apparcom.presentation.SolicitacoesViewModel
import br.com.arcom.apparcom.service.TokenService
import coil.ImageLoader
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun modulesAndroid(context: Context) = module {
    single<FirebaseCrashlytics> { FirebaseCrashlytics.getInstance() }
    single<FirebaseAnalytics> { FirebaseAnalytics.getInstance(context) }
    single<FirebaseMessaging> { FirebaseMessaging.getInstance() }
    single<TokenService> { AndroidTokenService(get()) }
    single<ImageLoader> { buildImageLoader(context) }


    viewModel { HomeViewModel() }
    viewModel { SolicitacoesViewModel() }
    viewModel { (id: String) -> DetalhesSolicitacaoViewModel(id) }
    viewModel { LoginViewModel() }
    viewModel { AppViewModel() }
}