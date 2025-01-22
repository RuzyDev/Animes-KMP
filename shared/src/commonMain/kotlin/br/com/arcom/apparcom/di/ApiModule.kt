package br.com.arcom.apparcom.di

import br.com.arcom.apparcom.network.KtorHttpClient
import br.com.arcom.apparcom.network.service.AppService
import br.com.arcom.apparcom.network.service.SolicitacaoService
import br.com.arcom.apparcom.network.service.UsuarioService
import br.com.arcom.apparcom.network.service.impl.AppServiceImpl
import br.com.arcom.apparcom.network.service.impl.SolicitacaoServiceImpl
import br.com.arcom.apparcom.network.service.impl.UsuarioServiceImpl
import io.ktor.client.HttpClient
import org.koin.dsl.module

fun apiModule(enableNetworkLogs: Boolean) = module {
    single<HttpClient> { KtorHttpClient(get(), get()).httpClient(enableNetworkLogs) }

    single<SolicitacaoService> { SolicitacaoServiceImpl(get()) }
    single<UsuarioService> { UsuarioServiceImpl(get()) }
    single<AppService> { AppServiceImpl(get()) }
}