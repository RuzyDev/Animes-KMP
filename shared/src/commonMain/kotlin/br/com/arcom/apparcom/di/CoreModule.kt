package br.com.arcom.apparcom.di

import br.com.arcom.apparcom.database.AppAnimeDatabase
import br.com.arcom.apparcom.db.dao.AnimeAiredDao
import br.com.arcom.apparcom.db.dao.AnimeDao
import br.com.arcom.apparcom.db.dao.AnimeGenreDao
import br.com.arcom.apparcom.db.dao.AnimeImageDao
import br.com.arcom.apparcom.db.dao.AnimeProducerDao
import br.com.arcom.apparcom.db.dao.AnimeQueryCrossRefDao
import br.com.arcom.apparcom.db.dao.AnimeQueryDao
import br.com.arcom.apparcom.db.dao.AnimeStudioDao
import br.com.arcom.apparcom.db.dao.AnimeTrailerDao
import br.com.arcom.apparcom.db.dao.impl.SolicitacaoAceiteDaoImpl
import br.com.arcom.apparcom.db.dao.SolicitacaoAceiteDao
import br.com.arcom.apparcom.db.dao.UsuarioDao
import br.com.arcom.apparcom.db.dao.impl.AnimeAiredDaoImpl
import br.com.arcom.apparcom.db.dao.impl.AnimeDaoImpl
import br.com.arcom.apparcom.db.dao.impl.AnimeGenreDaoImpl
import br.com.arcom.apparcom.db.dao.impl.AnimeImageDaoImpl
import br.com.arcom.apparcom.db.dao.impl.AnimeProducerDaoImpl
import br.com.arcom.apparcom.db.dao.impl.AnimeQueryCrossRefDaoImpl
import br.com.arcom.apparcom.db.dao.impl.AnimeQueryDaoImpl
import br.com.arcom.apparcom.db.dao.impl.AnimeStudioDaoImpl
import br.com.arcom.apparcom.db.dao.impl.AnimeTrailerDaoImpl
import br.com.arcom.apparcom.db.dao.impl.UsuarioDaoImpl
import br.com.arcom.apparcom.domain.interactor.AtualizarPushToken
import br.com.arcom.apparcom.domain.interactor.AtualizarVersaoApp
import br.com.arcom.apparcom.domain.interactor.UpdateTopAnimes
import br.com.arcom.apparcom.domain.interactor.GetUsuario
import br.com.arcom.apparcom.domain.interactor.RealizarAtualizacao
import br.com.arcom.apparcom.domain.interactor.RealizarLogin
import br.com.arcom.apparcom.domain.interactor.RegistrarSolicitacao
import br.com.arcom.apparcom.domain.interactor.UpdateSolicitacoes
import br.com.arcom.apparcom.domain.observers.ObserveDetalhesSolicitacao
import br.com.arcom.apparcom.domain.observers.ObserveQtdSolicitacoesPendentes
import br.com.arcom.apparcom.domain.observers.ObserveSolicitacoes
import br.com.arcom.apparcom.domain.observers.ObserveTopAnimes
import br.com.arcom.apparcom.domain.observers.ObserveUsuario
import br.com.arcom.apparcom.domain.repository.AnimeRepository
import br.com.arcom.apparcom.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.apparcom.domain.repository.UsuarioRepository
import br.com.arcom.apparcom.domain.repository.impl.AnimeRepositoryImpl
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
    single<AnimeRepository> {
        AnimeRepositoryImpl(
            animeService = get(),
            animeDao = get(),
            animeQueryDao = get(),
            animeQueryCrossRefDao = get(),
            animeAiredDao = get(),
            animeGenreDao = get(),
            animeImageDao = get(),
            animeProducerDao = get(),
            animeStudioDao = get(),
            animeTrailerDao = get(),
            preferencesManager = get()
        )
    }

    //-------------Daos------------------
    single<SolicitacaoAceiteDao> { SolicitacaoAceiteDaoImpl(solicitacaoAceiteQueries = get<AppAnimeDatabase>().solicitacaoAceiteQueries) }
    single<UsuarioDao> { UsuarioDaoImpl(usuarioQueries = get<AppAnimeDatabase>().usuarioQueries) }
    single<AnimeDao> { AnimeDaoImpl(animeQueries = get<AppAnimeDatabase>().animeQueries) }
    single<AnimeQueryDao> { AnimeQueryDaoImpl(animeQueryQueries = get<AppAnimeDatabase>().animeQueryQueries) }
    single<AnimeQueryCrossRefDao> { AnimeQueryCrossRefDaoImpl(animeQueryCrossRefQueries = get<AppAnimeDatabase>().animeQueryCrossRefQueries) }
    single<AnimeAiredDao> { AnimeAiredDaoImpl(animeAiredQueries = get<AppAnimeDatabase>().animeAiredQueries) }
    single<AnimeGenreDao> { AnimeGenreDaoImpl(animeGenreQueries = get<AppAnimeDatabase>().animeGenreQueries) }
    single<AnimeImageDao> { AnimeImageDaoImpl(animeImageQueries = get<AppAnimeDatabase>().animeImageQueries) }
    single<AnimeProducerDao> { AnimeProducerDaoImpl(animeProducerQueries = get<AppAnimeDatabase>().animeProducerQueries) }
    single<AnimeStudioDao> { AnimeStudioDaoImpl(animeProducerQueries = get<AppAnimeDatabase>().animeProducerQueries) }
    single<AnimeTrailerDao> { AnimeTrailerDaoImpl(animeTrailerQueries = get<AppAnimeDatabase>().animeTrailerQueries) }

    //------------Domains----------------
    //Solicitacao - Interactors
    single { UpdateSolicitacoes(get(), get()) }
    single { RegistrarSolicitacao(get(), get()) }
    single { RealizarLogin(get(), get()) }
    single { GetUsuario(get(), get()) }
    single { AtualizarPushToken(get(), get()) }
    single { AtualizarVersaoApp(get(), get(), get()) }
    single { UpdateTopAnimes(get(), get()) }
    //Solicitacao - Observers
    factory { ObserveTopAnimes(get()) }
    factory { ObserveSolicitacoes(get()) }
    factory { ObserveDetalhesSolicitacao(get()) }
    factory { ObserveUsuario(get()) }
    factory { ObserveQtdSolicitacoesPendentes(get()) }

    //Outros
    single { RealizarAtualizacao(get(), get(), get()) }
}