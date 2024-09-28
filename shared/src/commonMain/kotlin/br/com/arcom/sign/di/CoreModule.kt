package br.com.arcom.sign.di

import br.com.arcom.sign.database.AnimesHubDatabase
import br.com.arcom.sign.domain.interactor.anime.GetTopAnimeWithPage
import br.com.arcom.sign.domain.interactor.anime.UpdateTopAnimes
import br.com.arcom.sign.domain.interactor.manga.GetTopMangaWithPage
import br.com.arcom.sign.domain.interactor.manga.UpdateTopMangas
import br.com.arcom.sign.domain.observers.ObserveTopAnimes
import br.com.arcom.sign.domain.observers.ObserveTopMangas
import br.com.arcom.sign.domain.repository.TopRepository
import br.com.arcom.sign.domain.repository.impl.TopRepositoryImpl
import br.com.arcom.sign.util.AppCoroutineDispatchers
import br.com.arcom.sign.util.datastore.AnimesHubDataStore
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

    single<TopRepository> {
        TopRepositoryImpl(
            topService = get(),
            animeQueries = get<AnimesHubDatabase>().animeQueries,
            mangaQueries = get<AnimesHubDatabase>().mangaQueries,
            imageMangaQueries = get<AnimesHubDatabase>().imageMangaQueries,
            imageAnimeQueries = get<AnimesHubDatabase>().imageAnimeQueries,
            rankingAnimeQueries = get<AnimesHubDatabase>().rankingAnimeQueries,
            rankingMangaQueries = get<AnimesHubDatabase>().rankingMangaQueries
        )
    }

    //----------Domains--------------
    //Anime
    single { ObserveTopAnimes(get()) }
    single { UpdateTopAnimes(get(), get()) }
    single { GetTopAnimeWithPage(get(), get()) }
    //Manga
    single { ObserveTopMangas(get()) }
    single { UpdateTopMangas(get(), get()) }
    single { GetTopMangaWithPage(get(), get()) }
}