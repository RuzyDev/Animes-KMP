package br.com.arcom.apparcom.di

import br.com.arcom.apparcom.database.AppArcomDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { AppArcomDatabase.invoke(driver = get()) }
}