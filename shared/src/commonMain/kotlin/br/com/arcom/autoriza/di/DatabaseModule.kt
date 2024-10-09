package br.com.arcom.autoriza.di

import br.com.arcom.autoriza.database.AnimesHubDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { AnimesHubDatabase.invoke(driver = get()) }
}