package br.com.arcom.apparcom.presentation

import br.com.arcom.apparcom.domain.interactor.AtualizarPushToken
import br.com.arcom.apparcom.domain.interactor.RealizarAtualizacao
import br.com.arcom.apparcom.domain.interactor.UpdateTopAnimes
import br.com.arcom.apparcom.domain.observers.ObserveQtdSolicitacoesPendentes
import br.com.arcom.apparcom.domain.observers.ObserveTopAnimes
import br.com.arcom.apparcom.domain.observers.ObserveUsuario
import br.com.arcom.apparcom.model.AnimeWithQuery
import br.com.arcom.apparcom.model.FilterTopAnimes
import br.com.arcom.apparcom.model.Usuario
import br.com.arcom.apparcom.presentation.util.UiMessage
import br.com.arcom.apparcom.presentation.util.UiMessageManager
import br.com.arcom.apparcom.service.TokenService
import br.com.arcom.apparcom.util.ResultState
import br.com.arcom.apparcom.util.asResultState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : CoroutineViewModel(), KoinComponent {

    private val observeTopAnimesAiring: ObserveTopAnimes by inject()
    private val observeTopAnimesByRank: ObserveTopAnimes by inject()
    private val observeTopAnimesUpcoming: ObserveTopAnimes by inject()
    private val updateTopAnimes: UpdateTopAnimes by inject()
    private val tokenService: TokenService by inject()
    private val uiMessage = UiMessageManager()

    val uiState: StateFlow<HomeUiState> =
        combine(
            uiMessage.observable,
            observeTopAnimesAiring.flow.asResultState(),
            observeTopAnimesByRank.flow.asResultState(),
            observeTopAnimesUpcoming.flow.asResultState(),
            ::HomeUiState
        ).stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5000),
            HomeUiState.Empty
        )

    fun clearMessage(id: Long) {
        coroutineScope.launch {
            uiMessage.clearMessage(id)
        }
    }

    fun observeUiState(onChange: (HomeUiState) -> Unit) {
        uiState.onEach {
            onChange(it)
        }.launchIn(coroutineScope)
    }

    fun updateAnimes(){
        coroutineScope.launch {
            try {
                updateTopAnimes.invoke(UpdateTopAnimes.Params(FilterTopAnimes.AIRING, 1))
                updateTopAnimes.invoke(UpdateTopAnimes.Params(FilterTopAnimes.RANKING, 1))
                updateTopAnimes.invoke(UpdateTopAnimes.Params(FilterTopAnimes.UPCOMING, 1))
            }catch (e: Exception){
                uiMessage.emitMessage(e)
            }
        }
    }

    fun registrarPushToken() {
        coroutineScope.launch {
            tokenService.getToken()?.let {
                //API para enviar token firebase para servidor
            }
        }
    }

    init {
        observeTopAnimesAiring(ObserveTopAnimes.Params(FilterTopAnimes.AIRING))
        observeTopAnimesByRank(ObserveTopAnimes.Params(FilterTopAnimes.RANKING))
        observeTopAnimesUpcoming(ObserveTopAnimes.Params(FilterTopAnimes.UPCOMING))
        updateAnimes()
    }
}

data class HomeUiState(
    val uiMessage: UiMessage? = null,
    val topAnimesAiring: ResultState<AnimeWithQuery?> = ResultState.Loading,
    val topAnimesRanking: ResultState<AnimeWithQuery?> = ResultState.Loading,
    val topAnimesUpcoming: ResultState<AnimeWithQuery?> = ResultState.Loading,
) {
    companion object {
        val Empty = HomeUiState()
    }
}