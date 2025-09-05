package br.com.arcom.apparcom.presentation

import br.com.arcom.apparcom.domain.interactor.UpdateTopAnimes
import br.com.arcom.apparcom.presentation.util.UiMessage
import br.com.arcom.apparcom.presentation.util.UiMessageManager
import br.com.arcom.apparcom.service.TokenService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AnimeDetailsViewModel : CoroutineViewModel(), KoinComponent {

    private val updateTopAnimes: UpdateTopAnimes by inject()
    private val tokenService: TokenService by inject()
    private val uiMessage = UiMessageManager()

    val uiState: StateFlow<AnimeDetailsUiState> =
        uiMessage.observable.map { AnimeDetailsUiState(it) }
            .stateIn(
                coroutineScope,
                SharingStarted.WhileSubscribed(5000),
                AnimeDetailsUiState.Empty
            )

    fun clearMessage(id: Long) {
        coroutineScope.launch {
            uiMessage.clearMessage(id)
        }
    }

    fun observeUiState(onChange: (AnimeDetailsUiState) -> Unit) {
        uiState.onEach {
            onChange(it)
        }.launchIn(coroutineScope)
    }

    fun updateAnimes() {
        coroutineScope.launch {
            try {
            } catch (e: Exception) {
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
        updateAnimes()
    }
}

data class AnimeDetailsUiState(
    val uiMessage: UiMessage? = null
) {
    companion object {
        val Empty = AnimeDetailsUiState()
    }
}