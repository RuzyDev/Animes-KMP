package br.com.arcom.autoriza.presentation

import br.com.arcom.autoriza.domain.interactor.UpdateSolicitacoes
import br.com.arcom.autoriza.domain.observers.ObserveSolicitacoes
import br.com.arcom.autoriza.model.solicitacao.SolicitacaoAceite
import br.com.arcom.autoriza.util.ResultState
import br.com.arcom.autoriza.util.asResultState
import korlibs.io.async.launch
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SolicitacoesViewModel : CoroutineViewModel(), KoinComponent {

    private val observeSolicitacoes: ObserveSolicitacoes by inject()
    private val updateSolicitacoes: UpdateSolicitacoes by inject()


    val uiState: StateFlow<SolicitacoesUiState> =
        combine(
            updateSolicitacoes.inProgress,
            observeSolicitacoes.flow.asResultState(),
            ::SolicitacoesUiState
        ).stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5000),
            SolicitacoesUiState.Empty
        )

    fun refresh() {
        coroutineScope.launch {
            updateSolicitacoes.invoke(UpdateSolicitacoes.Params(159707, 0))
        }
    }

    init {
        refresh()
        observeSolicitacoes(ObserveSolicitacoes.Params())
    }
}

data class SolicitacoesUiState(
    val loadingSolicitacoes: Boolean = false,
    val topAnimes: ResultState<List<SolicitacaoAceite>> = ResultState.Loading
) {
    companion object {
        val Empty = SolicitacoesUiState()
    }
}