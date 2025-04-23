package br.com.arcom.apparcom.presentation

import br.com.arcom.apparcom.domain.collectStatus
import br.com.arcom.apparcom.domain.interactor.GetUsuario
import br.com.arcom.apparcom.domain.interactor.RegistrarSolicitacao
import br.com.arcom.apparcom.domain.interactor.UpdateSolicitacoes
import br.com.arcom.apparcom.domain.observers.ObserveSolicitacoes
import br.com.arcom.apparcom.model.Usuario
import br.com.arcom.apparcom.model.solicitacao.SolicitacaoAceite
import br.com.arcom.apparcom.model.solicitacao.StatusSolicitacao
import br.com.arcom.apparcom.model.solicitacao.TipoSolicitacao
import br.com.arcom.apparcom.presentation.util.UiMessage
import br.com.arcom.apparcom.presentation.util.UiMessageManager
import br.com.arcom.apparcom.util.format.dataHoraAtual
import korlibs.io.async.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SolicitacoesViewModel : CoroutineViewModel(), KoinComponent {

    private val observeSolicitacoes: ObserveSolicitacoes by inject()
    private val updateSolicitacoes: UpdateSolicitacoes by inject()
    private val registrarSolicitacao: RegistrarSolicitacao by inject()
    private val getUsuario: GetUsuario by inject()

    private val uiMessage = UiMessageManager()
    private val _pesquisa = MutableStateFlow("" to TipoSolicitacao.TODOS)
    private val _usuario = MutableStateFlow<Usuario?>(null)
    private val _page = MutableStateFlow(PageSolicitacao.Empty)

    val uiState: StateFlow<SolicitacoesUiState> =
        combine(
            updateSolicitacoes.inProgress,
            registrarSolicitacao.inProgress,
            observeSolicitacoes.flow,
            uiMessage.observable,
            _page,
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

    fun getUsuario(){
        coroutineScope.launch {
            val usuario = getUsuario.invoke(Unit).getOrNull()
            _usuario.emit(usuario)
            buscarSolicitacoes(1)
        }
    }

    private suspend fun buscarSolicitacoes(
        page: Long,
        tipoSolicitacao: TipoSolicitacao = TipoSolicitacao.TODOS,
        callback: (() -> Unit) = {}
    ) {
       val usuario = _usuario.value
       if (usuario != null) {
           val pageMax = updateSolicitacoes.invoke(
               UpdateSolicitacoes.Params(
                   usuario.id,
                   page,
                   tipoSolicitacao
               )
           )
           _page.emit(PageSolicitacao(page, pageMax.getOrNull() ?: 0))
           callback()
       } else {
           uiMessage.emitMessage(UiMessage(message = "Usuário não encontrado!"))
       }
    }

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

    fun clearMessage(id: Long) {
        coroutineScope.launch {
            uiMessage.clearMessage(id)
        }
    }

    fun setPage(page: Long) {
        _page.update {
            it.copy(page = page)
        }
    }

    fun setSearch(search: String) {
        _pesquisa.update {
            search to it.second
        }
    }

    fun setFiltro(filtro: TipoSolicitacao) {
       _pesquisa.update {
           it.first to filtro
       }
    }

    init {
        getUsuario()

        combine(_pesquisa, _page, ::Pair).onEach { (pesquisa, page) ->
            buscarSolicitacoes(page = page.page, tipoSolicitacao = pesquisa.second)
        }.launchIn(coroutineScope)

        combine(_pesquisa, _page, ::Pair).onEach { (pesquisa, page) ->
            observeSolicitacoes(
                ObserveSolicitacoes.Params(
                    search = pesquisa.first,
                    filtro = pesquisa.second,
                    page = page.page
                )
            )
        }.launchIn(coroutineScope)
    }
}

data class PageSolicitacao(
    val page: Long,
    val totalPaginas: Long
) {
    companion object {
        val Empty = PageSolicitacao(1, 1)
    }
}

data class SolicitacoesUiState(
    val loadingSolicitacoes: Boolean = false,
    val loadingRegistrando: Boolean = false,
    val solicitacoes: List<SolicitacaoAceite> = emptyList(),
    val uiMessage: UiMessage? = null,
    val paginacao: PageSolicitacao = PageSolicitacao.Empty
) {
    companion object {
        val Empty = SolicitacoesUiState()
    }
}