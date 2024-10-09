package br.com.arcom.autoriza.di

import br.com.arcom.autoriza.presentation.HomeViewModel
import org.koin.dsl.module

val uiModule = module {
    factory { HomeViewModel() }
}