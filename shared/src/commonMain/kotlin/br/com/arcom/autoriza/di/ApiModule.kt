package br.com.arcom.autoriza.di

import br.com.arcom.autoriza.network.KtorHttpClient
import br.com.arcom.autoriza.network.service.SolicitacaoService
import br.com.arcom.autoriza.network.service.UsuarioService
import br.com.arcom.autoriza.network.service.impl.SolicitacaoServiceImpl
import br.com.arcom.autoriza.network.service.impl.UsuarioServiceImpl
import io.ktor.client.HttpClient
import org.koin.dsl.module

fun apiModule(enableNetworkLogs: Boolean) = module {
    single<HttpClient> { KtorHttpClient(get()).httpClient(enableNetworkLogs) }

    single<SolicitacaoService> { SolicitacaoServiceImpl(get()) }
    single<UsuarioService> { UsuarioServiceImpl(get()) }
}