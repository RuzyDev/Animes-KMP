package br.com.arcom.sign.di

import br.com.arcom.sign.database.AnimesHubDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { AnimesHubDatabase.invoke(driver = get()) }
}