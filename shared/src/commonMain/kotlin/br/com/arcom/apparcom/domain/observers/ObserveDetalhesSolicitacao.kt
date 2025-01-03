package br.com.arcom.apparcom.domain.observers

import br.com.arcom.apparcom.domain.SubjectInteractor
import br.com.arcom.apparcom.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.apparcom.model.solicitacao.SolicitacaoAceite
import kotlinx.coroutines.flow.Flow

class ObserveDetalhesSolicitacao(
    private val solicitacaoAceiteRepository: SolicitacaoAceiteRepository
) : SubjectInteractor<ObserveDetalhesSolicitacao.Params, SolicitacaoAceite>() {

    data class Params(
        val id: String
    )

    override fun createObservable(params: Params): Flow<SolicitacaoAceite> {
        return solicitacaoAceiteRepository.observeSolicitacaoAceite(params.id)
    }
}
