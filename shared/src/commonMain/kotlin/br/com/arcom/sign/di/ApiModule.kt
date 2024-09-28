package br.com.arcom.sign.di

import br.com.arcom.sign.network.KtorHttpClient
import br.com.arcom.sign.network.service.impl.TopServiceImpl
import br.com.arcom.sign.network.service.TopService
import io.ktor.client.HttpClient
import org.koin.dsl.module

fun apiModule(enableNetworkLogs: Boolean) = module {
    single<HttpClient> { KtorHttpClient(get()).httpClient(enableNetworkLogs) }

    single<TopService> { TopServiceImpl(get()) }
}