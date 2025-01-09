package br.com.arcom.apparcom.domain.interactor

import br.com.arcom.apparcom.domain.Interactor
import br.com.arcom.apparcom.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.apparcom.model.solicitacao.SolicitacaoAceite
import br.com.arcom.apparcom.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class RegistrarSolicitacao(
    private val solicitacaoAceiteRepository: SolicitacaoAceiteRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<RegistrarSolicitacao.Params, Unit>() {

    data class Params(
        val solicitacao: SolicitacaoAceite,
        val idUsuario: Long
    )

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io){
            solicitacaoAceiteRepository.registrarSolicitacao(params.solicitacao, params.idUsuario)
        }
    }
}