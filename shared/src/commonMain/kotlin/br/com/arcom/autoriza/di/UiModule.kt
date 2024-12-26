package br.com.arcom.autoriza.di

import br.com.arcom.autoriza.presentation.AppViewModel
import br.com.arcom.autoriza.presentation.DetalhesSolicitacaoViewModel
import br.com.arcom.autoriza.presentation.HomeViewModel
import br.com.arcom.autoriza.presentation.LoginViewModel
import br.com.arcom.autoriza.presentation.SolicitacoesViewModel
import org.koin.dsl.module

val uiModule = module {
    factory { HomeViewModel() }
    factory { SolicitacoesViewModel() }
    factory { (id: String) -> DetalhesSolicitacaoViewModel(id) }
    factory { LoginViewModel() }
    factory { AppViewModel() }
}