package br.com.arcom.apparcom.domain.observers

import br.com.arcom.apparcom.domain.SubjectInteractor
import br.com.arcom.apparcom.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.apparcom.model.solicitacao.SolicitacaoAceite
import br.com.arcom.apparcom.model.solicitacao.TipoSolicitacao
import kotlinx.coroutines.flow.Flow

class ObserveSolicitacoes(
    private val solicitacaoAceiteRepository: SolicitacaoAceiteRepository
) : SubjectInteractor<ObserveSolicitacoes.Params, List<SolicitacaoAceite>>() {

    data class Params(
        val page: Long = 0,
        val search: String = "",
        val filtro: TipoSolicitacao = TipoSolicitacao.TODOS
    )

    override fun createObservable(params: Params): Flow<List<SolicitacaoAceite>> {
        return solicitacaoAceiteRepository.observeSolicitacoesAceite(
            page = params.page,
            search = params.search,
            filtro = params.filtro
        )
    }
}
