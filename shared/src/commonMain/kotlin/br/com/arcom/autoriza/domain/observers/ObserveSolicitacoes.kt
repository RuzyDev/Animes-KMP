package br.com.arcom.autoriza.domain.observers

import br.com.arcom.autoriza.domain.SubjectInteractor
import br.com.arcom.autoriza.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.autoriza.model.solicitacao.SolicitacaoAceite
import kotlinx.coroutines.flow.Flow

class ObserveSolicitacoes(
    private val solicitacaoAceiteRepository: SolicitacaoAceiteRepository
) : SubjectInteractor<ObserveSolicitacoes.Params, List<SolicitacaoAceite>>() {

    data class Params(
        val page: Long = 0
    )

    override fun createObservable(params: Params): Flow<List<SolicitacaoAceite>> {
        return solicitacaoAceiteRepository.observeSolicitacaoAceite(page = params.page)
    }
}
