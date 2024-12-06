package br.com.arcom.autoriza.presentation

import br.com.arcom.autoriza.domain.collectStatus
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

class SolicitacoesViewModel : CoroutineViewModel(), KoinComponent {

    private val observeSolicitacoes: ObserveSolicitacoes by inject()
    private val updateSolicitacoes: UpdateSolicitacoes by inject()
    private val registrarSolicitacao: RegistrarSolicitacao by inject()

    private val uiMessage = UiMessageManager()

    val uiState: StateFlow<SolicitacoesUiState> =
        combine(
            updateSolicitacoes.inProgress,
            registrarSolicitacao.inProgress,
            observeSolicitacoes.flow,
            uiMessage.observable,
            ::SolicitacoesUiState
        ).stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5000),
            SolicitacoesUiState.Empty
        )

    fun observeUiState(onChange: (SolicitacoesUiState) -> Unit) {
        uiState.onEach {
            onChange(it)
        }.launchIn(coroutineScope)
    }

    fun refresh() {
        coroutineScope.launch {
            updateSolicitacoes.invoke(UpdateSolicitacoes.Params(145078, 0))
        }
    }

    fun responderSolicitacao(solicitacao: SolicitacaoAceite, reposta: Boolean) {
        val result = solicitacao.copy(
            status = if (reposta) StatusSolicitacao.APROVADO else StatusSolicitacao.NEGADO,
            dataResposta = dataHoraAtual()
        )
        coroutineScope.launch {
            registrarSolicitacao.invoke(RegistrarSolicitacao.Params(result)).collectStatus(uiMessage)
        }
    }

    fun clearMessage(id: Long) {
        coroutineScope.launch {
            uiMessage.clearMessage(id)
        }
    }

    init {
        refresh()
        observeSolicitacoes(ObserveSolicitacoes.Params())
    }
}

data class SolicitacoesUiState(
    val loadingSolicitacoes: Boolean = false,
    val loadingRegistando: Boolean = false,
    val solicitacoes: List<SolicitacaoAceite> = emptyList(),
    val uiMessage: UiMessage? = null
) {
    companion object {
        val Empty = SolicitacoesUiState()
    }
}