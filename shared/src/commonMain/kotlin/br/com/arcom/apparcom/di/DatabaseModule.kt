package br.com.arcom.apparcom.di

import br.com.arcom.apparcom.database.AppAnimeDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { AppAnimeDatabase.invoke(driver = get()) }
}