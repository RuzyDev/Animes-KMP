package br.com.arcom.apparcom.di

import br.com.arcom.apparcom.presentation.AppViewModel
import br.com.arcom.apparcom.presentation.DetalhesSolicitacaoViewModel
import br.com.arcom.apparcom.presentation.HomeViewModel
import br.com.arcom.apparcom.presentation.LoginViewModel
import br.com.arcom.apparcom.presentation.SolicitacoesViewModel
import org.koin.dsl.module

val uiModule = module {
    factory { HomeViewModel() }
    factory { SolicitacoesViewModel() }
    factory { (id: String) -> DetalhesSolicitacaoViewModel(id) }
    factory { LoginViewModel() }
    factory { AppViewModel() }
}