package br.com.arcom.autoriza.di

import br.com.arcom.autoriza.database.AppArcomDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { AppArcomDatabase.invoke(driver = get()) }
}