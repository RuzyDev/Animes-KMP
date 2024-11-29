package br.com.arcom.autoriza.domain.interactor

import br.com.arcom.autoriza.domain.Interactor
import br.com.arcom.autoriza.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.autoriza.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class UpdateSolicitacoes(
    private val solicitacaoAceiteRepository: SolicitacaoAceiteRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<UpdateSolicitacoes.Params, Unit>() {

    data class Params(
        val idUsuario: Long,
        val page: Short
    )

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io){
            solicitacaoAceiteRepository.updateSolicitacaoAceite(
                idUsuario = params.idUsuario,
                page = params.page
            )
        }
    }
}