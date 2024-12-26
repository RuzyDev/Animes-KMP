package br.com.arcom.autoriza.di

import br.com.arcom.autoriza.database.AppArcomDatabase
import br.com.arcom.autoriza.db.dao.impl.SolicitacaoAceiteDaoImpl
import br.com.arcom.autoriza.db.dao.SolicitacaoAceiteDao
import br.com.arcom.autoriza.db.dao.UsuarioDao
import br.com.arcom.autoriza.db.dao.impl.UsuarioDaoImpl
import br.com.arcom.autoriza.domain.interactor.RealizarLogin
import br.com.arcom.autoriza.domain.interactor.RegistrarSolicitacao
import br.com.arcom.autoriza.domain.interactor.UpdateSolicitacoes
import br.com.arcom.autoriza.domain.observers.ObserveDetalhesSolicitacao
import br.com.arcom.autoriza.domain.observers.ObserveSolicitacoes
import br.com.arcom.autoriza.domain.observers.ObserveUsuario
import br.com.arcom.autoriza.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.autoriza.domain.repository.UsuarioRepository
import br.com.arcom.autoriza.domain.repository.impl.SolicitacaoAceiteRepositoryImpl
import br.com.arcom.autoriza.domain.repository.impl.UsuarioRepositoryImpl
import br.com.arcom.autoriza.util.AppCoroutineDispatchers
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

    //----------Repositorys--------------
    single<SolicitacaoAceiteRepository> {
        SolicitacaoAceiteRepositoryImpl(
            solicitacaoService = get(),
            solicitacaoAceiteDao = get()
        )
    }
    single<UsuarioRepository> {
        UsuarioRepositoryImpl(
            usuarioService = get(),
            usuarioDao = get(),
            appArcomStorage = get(),
            preferencesManager = get()
        )
    }

    //-------------Daos------------------
    single<SolicitacaoAceiteDao> {
        SolicitacaoAceiteDaoImpl(
            solicitacaoAceiteQueries = get<AppArcomDatabase>().solicitacaoAceiteQueries
        )
    }
    single<UsuarioDao> {
        UsuarioDaoImpl(
            usuarioQueries = get<AppArcomDatabase>().usuarioQueries
        )
    }

    //------------Domains----------------
    //Solicitacao - Interactors
    single { UpdateSolicitacoes(get(), get()) }
    single { RegistrarSolicitacao(get(), get()) }
    single { RealizarLogin(get(), get()) }
    //Solicitacao - Observers
    single { ObserveSolicitacoes(get()) }
    single { ObserveDetalhesSolicitacao(get()) }
    single { ObserveUsuario(get()) }
}