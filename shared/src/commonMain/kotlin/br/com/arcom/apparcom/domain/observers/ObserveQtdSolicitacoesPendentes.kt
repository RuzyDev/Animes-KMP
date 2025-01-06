package br.com.arcom.apparcom.domain.observers

import br.com.arcom.apparcom.domain.SubjectInteractor
import br.com.arcom.apparcom.domain.repository.SolicitacaoAceiteRepository
import kotlinx.coroutines.flow.Flow

class ObserveQtdSolicitacoesPendentes(
    private val solicitacaoAceiteRepository: SolicitacaoAceiteRepository
) : SubjectInteractor<Unit, Long>() {
    override fun createObservable(params: Unit): Flow<Long> {
        return solicitacaoAceiteRepository.observeQtdSolicitacoesPendentes()
    }
}
