package br.com.arcom.autoriza.domain.interactor

import br.com.arcom.autoriza.domain.Interactor
import br.com.arcom.autoriza.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.autoriza.model.solicitacao.SolicitacaoAceite
import br.com.arcom.autoriza.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class RegistrarSolicitacao(
    private val solicitacaoAceiteRepository: SolicitacaoAceiteRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<RegistrarSolicitacao.Params, Unit>() {

    data class Params(
        val solicitacao: SolicitacaoAceite
    )

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io){
            solicitacaoAceiteRepository.registrarSolicitacao(params.solicitacao)
        }
    }
}