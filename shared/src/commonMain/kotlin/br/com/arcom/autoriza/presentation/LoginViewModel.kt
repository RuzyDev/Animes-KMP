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
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel : CoroutineViewModel(), KoinComponent {

    private val realizarLogin: RealizarLogin by inject()

    private val uiMessage = UiMessageManager()

    val uiState: StateFlow<UiMessage?> =
        uiMessage.observable.stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    fun realizarLogin(idUsuario: Long, senha: String, navigateToHome: () -> Unit) {
        coroutineScope.launch {
            realizarLogin.invoke(RealizarLogin.Params(idUsuario, senha))
                .collectStatus(uiMessage, onSuccess = { navigateToHome() })
        }
    }

    fun clearMessage(id: Long) {
        coroutineScope.launch {
            uiMessage.clearMessage(id)
        }
    }
}
