package br.com.arcom.autoriza.di

import br.com.arcom.autoriza.presentation.HomeViewModel
import br.com.arcom.autoriza.presentation.SolicitacoesViewModel
import org.koin.dsl.module

val uiModule = module {
    factory { HomeViewModel() }
    factory { SolicitacoesViewModel() }
}