package br.com.arcom.apparcom.presentation

import br.com.arcom.apparcom.domain.interactor.AtualizarPushToken
import br.com.arcom.apparcom.domain.interactor.RealizarAtualizacao
import br.com.arcom.apparcom.domain.observers.ObserveQtdSolicitacoesPendentes
import br.com.arcom.apparcom.domain.observers.ObserveUsuario
import br.com.arcom.apparcom.model.Usuario
import br.com.arcom.apparcom.presentation.util.UiMessage
import br.com.arcom.apparcom.presentation.util.UiMessageManager
import br.com.arcom.apparcom.service.TokenService
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

    private val observeUsuario: ObserveUsuario by inject()
    private val atualizarPushToken: AtualizarPushToken by inject()
    private val tokenService: TokenService by inject()
    private val uiMessage = UiMessageManager()

    val uiState: StateFlow<HomeUiState> =
        combine(
            uiMessage.observable,
            observeUsuario.flow,
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

    fun registrarPushToken() {
        coroutineScope.launch {
            tokenService.getToken()?.let {
                atualizarPushToken.invoke(AtualizarPushToken.Params(it))
            }
        }
    }

    init {
        observeUsuario(Unit)
    }
}

data class HomeUiState(
    val uiMessage: UiMessage? = null,
    val usuario: Usuario? = null
) {
    companion object {
        val Empty = HomeUiState()
    }
}