package br.com.arcom.apparcom.di

import br.com.arcom.apparcom.database.AppArcomDatabase
import br.com.arcom.apparcom.db.dao.impl.SolicitacaoAceiteDaoImpl
import br.com.arcom.apparcom.db.dao.SolicitacaoAceiteDao
import br.com.arcom.apparcom.db.dao.UsuarioDao
import br.com.arcom.apparcom.db.dao.impl.UsuarioDaoImpl
import br.com.arcom.apparcom.domain.interactor.RealizarLogin
import br.com.arcom.apparcom.domain.interactor.RegistrarSolicitacao
import br.com.arcom.apparcom.domain.interactor.UpdateSolicitacoes
import br.com.arcom.apparcom.domain.observers.ObserveDetalhesSolicitacao
import br.com.arcom.apparcom.domain.observers.ObserveSolicitacoes
import br.com.arcom.apparcom.domain.observers.ObserveUsuario
import br.com.arcom.apparcom.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.apparcom.domain.repository.UsuarioRepository
import br.com.arcom.apparcom.domain.repository.impl.SolicitacaoAceiteRepositoryImpl
import br.com.arcom.apparcom.domain.repository.impl.UsuarioRepositoryImpl
import br.com.arcom.apparcom.util.AppCoroutineDispatchers
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