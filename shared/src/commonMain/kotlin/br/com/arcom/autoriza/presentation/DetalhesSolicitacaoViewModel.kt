package br.com.arcom.autoriza.presentation

import br.com.arcom.autoriza.domain.collectStatus
import br.com.arcom.autoriza.domain.interactor.RegistrarSolicitacao
import br.com.arcom.autoriza.domain.observers.ObserveDetalhesSolicitacao
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

class DetalhesSolicitacaoViewModel(
    val idSolicitacao: String
) : CoroutineViewModel(), KoinComponent {

    private val observeDetalhesSolicitacao: ObserveDetalhesSolicitacao by inject()
    private val registrarSolicitacao: RegistrarSolicitacao by inject()

    private val uiMessage = UiMessageManager()

    val uiState: StateFlow<DetalhesSolicitacaoUiState> =
        combine(
            registrarSolicitacao.inProgress,
            observeDetalhesSolicitacao.flow.asResultState(),
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
            registrarSolicitacao.invoke(RegistrarSolicitacao.Params(result)).collectStatus(uiMessage)
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
}

data class DetalhesSolicitacaoUiState(
    val loadingRegistrando: Boolean = false,
    val solicitacao: ResultState<SolicitacaoAceite> = ResultState.Loading,
    val uiMessage: UiMessage? = null
) {
    companion object {
        val Empty = DetalhesSolicitacaoUiState()
    }
}