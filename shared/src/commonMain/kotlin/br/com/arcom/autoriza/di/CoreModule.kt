package br.com.arcom.autoriza.di

import br.com.arcom.autoriza.domain.interactor.UpdateSolicitacoes
import br.com.arcom.autoriza.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.autoriza.domain.repository.impl.SolicitacaoAceiteRepositoryImpl
import br.com.arcom.autoriza.util.AppCoroutineDispatchers
import br.com.arcom.autoriza.util.datastore.AnimesHubDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val coreModule = module {
    single { Json { ignoreUnknownKeys = true } }
    single {
        AppCoroutineDispatchers(
            io = Dispatchers.IO,
            computation = Dispatchers.Default,
            main = Dispatchers.Main
        )
    }
    single { AnimesHubDataStore(get()) }


    //----------Repositorys--------------
    single<SolicitacaoAceiteRepository> {
        SolicitacaoAceiteRepositoryImpl(
            solicitacaoService = get()
        )
    }

    //----------Domains--------------
    //Solicitacao
    single { UpdateSolicitacoes(get()) }
}