package br.com.arcom.autoriza.presentation

import br.com.arcom.autoriza.domain.collectStatus
import br.com.arcom.autoriza.domain.interactor.RealizarLogin
import br.com.arcom.autoriza.domain.interactor.RegistrarSolicitacao
import br.com.arcom.autoriza.domain.interactor.UpdateSolicitacoes
import br.com.arcom.autoriza.domain.observers.ObserveSolicitacoes
import br.com.arcom.autoriza.model.solicitacao.SolicitacaoAceite
import br.com.arcom.autoriza.model.solicitacao.StatusSolicitacao
import br.com.arcom.autoriza.presentation.util.UiMessage
import br.com.arcom.autoriza.presentation.util.UiMessageManager
import br.com.arcom.autoriza.util.ResultState
import br.com.arcom.autoriza.util.asResultState
import br.com.arcom.autoriza.util.format.dataHoraAtual
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
