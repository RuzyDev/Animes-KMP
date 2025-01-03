package br.com.arcom.apparcom.presentation

import br.com.arcom.apparcom.domain.collectStatus
import br.com.arcom.apparcom.domain.interactor.RealizarLogin
import br.com.arcom.apparcom.presentation.util.UiMessage
import br.com.arcom.apparcom.presentation.util.UiMessageManager
import korlibs.io.async.launch
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel : CoroutineViewModel(), KoinComponent {

    private val realizarLogin: RealizarLogin by inject()

    private val uiMessage = UiMessageManager()

    val uiState: StateFlow<LoginUiState> =
        combine(
            uiMessage.observable,
            realizarLogin.inProgress,
            ::LoginUiState
        ).stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5000),
            LoginUiState.Empty
        )

    fun realizarLogin(idUsuario: Long, senha: String) {
        coroutineScope.launch {
            realizarLogin.invoke(RealizarLogin.Params(idUsuario, senha))
                .collectStatus(uiMessage)
        }
    }

    fun clearMessage(id: Long) {
        coroutineScope.launch {
            uiMessage.clearMessage(id)
        }
    }

    fun observeUiState(onChange: (LoginUiState) -> Unit) {
        uiState.onEach {
            onChange(it)
        }.launchIn(coroutineScope)
    }
}

data class LoginUiState(
    val uiMessage: UiMessage?,
    val loadingLogin: Boolean
) {
    companion object {
        val Empty = LoginUiState(
            uiMessage = null,
            loadingLogin = false
        )
    }
}
