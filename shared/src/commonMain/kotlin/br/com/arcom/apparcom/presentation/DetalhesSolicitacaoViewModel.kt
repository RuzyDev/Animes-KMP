package br.com.arcom.apparcom.presentation

import br.com.arcom.apparcom.domain.collectStatus
import br.com.arcom.apparcom.domain.interactor.GetUsuario
import br.com.arcom.apparcom.domain.interactor.RegistrarSolicitacao
import br.com.arcom.apparcom.domain.observers.ObserveDetalhesSolicitacao
import br.com.arcom.apparcom.model.solicitacao.SolicitacaoAceite
import br.com.arcom.apparcom.model.solicitacao.StatusSolicitacao
import br.com.arcom.apparcom.presentation.util.UiMessage
import br.com.arcom.apparcom.presentation.util.UiMessageManager
import br.com.arcom.apparcom.util.format.dataHoraAtual
import korlibs.io.async.launch
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetalhesSolicitacaoViewModel(
    val idSolicitacao: String
) : CoroutineViewModel(), KoinComponent {

    private val observeDetalhesSolicitacao: ObserveDetalhesSolicitacao by inject()
    private val registrarSolicitacao: RegistrarSolicitacao by inject()
    private val getUsuario:GetUsuario by inject()

    private val uiMessage = UiMessageManager()

    val uiState: StateFlow<DetalhesSolicitacaoUiState> =
        combine(
            registrarSolicitacao.inProgress,
            observeDetalhesSolicitacao.flow,
            uiMessage.observable,
            ::DetalhesSolicitacaoUiState
        ).stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5000),
            DetalhesSolicitacaoUiState.Empty
        )

    fun responderSolicitacao(solicitacao: SolicitacaoAceite, reposta: Boolean) {
        val result = solicitacao.copy(
            status = if (reposta) StatusSolicitacao.APROVADO else StatusSolicitacao.NEGADO,
            dataResposta = dataHoraAtual()
        )
        coroutineScope.launch {
            val usuario = getUsuario.invoke(Unit).getOrNull()
            if (usuario != null) {
                registrarSolicitacao.invoke(RegistrarSolicitacao.Params(result, usuario.id))
                    .collectStatus(uiMessage)
            } else {
                uiMessage.emitMessage(UiMessage(message = "Usuário não encontrado!"))
            }
        }
    }

    fun clearMessage(id: Long){
        coroutineScope.launch {
            uiMessage.clearMessage(id)
        }
    }

    init {
        observeDetalhesSolicitacao(ObserveDetalhesSolicitacao.Params(idSolicitacao))
    }

    fun observeUiState(onChange: (DetalhesSolicitacaoUiState) -> Unit) {
        uiState.onEach {
            onChange(it)
        }.launchIn(coroutineScope)
    }
}

data class DetalhesSolicitacaoUiState(
    val loadingRegistrando: Boolean = false,
    val solicitacao: SolicitacaoAceite? = null,
    val uiMessage: UiMessage? = null
) {
    companion object {
        val Empty = DetalhesSolicitacaoUiState()
    }
}