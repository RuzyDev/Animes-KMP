package br.com.arcom.autoriza.di

import br.com.arcom.autoriza.network.KtorHttpClient
import br.com.arcom.autoriza.network.service.impl.TopServiceImpl
import br.com.arcom.autoriza.network.service.TopService
import io.ktor.client.HttpClient
import org.koin.dsl.module

fun apiModule(enableNetworkLogs: Boolean) = module {
    single<HttpClient> { KtorHttpClient(get()).httpClient(enableNetworkLogs) }

    single<TopService> { TopServiceImpl(get()) }
}