package br.com.arcom.autoriza.presentation

import org.koin.core.component.KoinComponent

class HomeViewModel : CoroutineViewModel(), KoinComponent {

//    private val updateTopAnimes: UpdateTopAnimes by inject()
//    private val updateTopMangas: UpdateTopMangas by inject()

//
//    val uiState: StateFlow<HomeUiState> =
//        combine(
//            updateTopAnimes.inProgress,
//            updateTopMangas.inProgress,
//            observeTopAnimes.flow.asResultState(),
//            observeTopMangas.flow.asResultState(),
//            ::HomeUiState
//        ).stateIn(
//            coroutineScope,
//            SharingStarted.WhileSubscribed(5000),
//            HomeUiState.Empty
//        )
//
//    fun refresh() {
//        coroutineScope.launch {
//            updateTopAnimes.invoke(UpdateTopAnimes.Params(TypeRakingAnime.BY_POPULARITY))
//            updateTopMangas.invoke(UpdateTopMangas.Params(TypeRakingManga.BY_POPULARITY))
//        }
//    }
//
//    init {
//        refresh()
//        observeTopAnimes(ObserveTopAnimes.Params(TypeRakingAnime.BY_POPULARITY, 0))
//        observeTopMangas(ObserveTopMangas.Params(TypeRakingManga.BY_POPULARITY, 0))
//    }
}

//data class HomeUiState(
//    val loadingTopAnimes: Boolean = false,
//    val loadingTopMangas: Boolean = false,
//    val topAnimes: ResultState<List<AnimeDetails>> = ResultState.Loading,
//    val topMangas: ResultState<List<MangaDetails>> = ResultState.Loading
//) {
//    companion object {
//        val Empty = HomeUiState()
//    }
//}