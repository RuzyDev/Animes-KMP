package br.com.arcom.autoriza.domain.observers

import br.com.arcom.autoriza.domain.SubjectInteractor
import br.com.arcom.autoriza.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.autoriza.model.solicitacao.SolicitacaoAceite
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
