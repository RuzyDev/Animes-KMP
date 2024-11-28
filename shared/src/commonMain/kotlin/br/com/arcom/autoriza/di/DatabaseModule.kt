package br.com.arcom.autoriza.di

import br.com.arcom.autoriza.database.AutorizaDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { AutorizaDatabase.invoke(driver = get()) }
}