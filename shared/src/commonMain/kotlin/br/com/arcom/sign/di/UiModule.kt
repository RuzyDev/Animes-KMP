package br.com.arcom.sign.di

import br.com.arcom.sign.presentation.HomeViewModel
import org.koin.dsl.module

val uiModule = module {
    factory { HomeViewModel() }
}