package br.com.arcom.sign.presentation

import br.com.arcom.sign.domain.interactor.anime.UpdateTopAnimes
import br.com.arcom.sign.domain.interactor.manga.UpdateTopMangas
import br.com.arcom.sign.domain.observers.ObserveTopAnimes
import br.com.arcom.sign.domain.observers.ObserveTopMangas
import br.com.arcom.sign.model.anime.AnimeDetails
import br.com.arcom.sign.model.manga.MangaDetails
import br.com.arcom.sign.model.ranking.TypeRakingAnime
import br.com.arcom.sign.model.ranking.TypeRakingManga
import br.com.arcom.sign.util.ResultState
import br.com.arcom.sign.util.asResultState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : CoroutineViewModel(), KoinComponent {

    private val updateTopAnimes: UpdateTopAnimes by inject()
    private val updateTopMangas: UpdateTopMangas by inject()
    private val observeTopAnimes: ObserveTopAnimes by inject()
    private val observeTopMangas: ObserveTopMangas by inject()


    val uiState: StateFlow<HomeUiState> =
        combine(
            updateTopAnimes.inProgress,
            updateTopMangas.inProgress,
            observeTopAnimes.flow.asResultState(),
            observeTopMangas.flow.asResultState(),
            ::HomeUiState
        ).stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5000),
            HomeUiState.Empty
        )

    fun refresh() {
        coroutineScope.launch {
            updateTopAnimes.invoke(UpdateTopAnimes.Params(TypeRakingAnime.BY_POPULARITY))
            updateTopMangas.invoke(UpdateTopMangas.Params(TypeRakingManga.BY_POPULARITY))
        }
    }

    init {
        refresh()
        observeTopAnimes(ObserveTopAnimes.Params(TypeRakingAnime.BY_POPULARITY, 0))
        observeTopMangas(ObserveTopMangas.Params(TypeRakingManga.BY_POPULARITY, 0))
    }
}

data class HomeUiState(
    val loadingTopAnimes: Boolean = false,
    val loadingTopMangas: Boolean = false,
    val topAnimes: ResultState<List<AnimeDetails>> = ResultState.Loading,
    val topMangas: ResultState<List<MangaDetails>> = ResultState.Loading
) {
    companion object {
        val Empty = HomeUiState()
    }
}