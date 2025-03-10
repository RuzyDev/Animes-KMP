package br.com.arcom.apparcom.domain.interactor

import br.com.arcom.apparcom.domain.Interactor
import br.com.arcom.apparcom.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.apparcom.model.solicitacao.TipoSolicitacao
import br.com.arcom.apparcom.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class UpdateSolicitacoes(
    private val solicitacaoAceiteRepository: SolicitacaoAceiteRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<UpdateSolicitacoes.Params, Long>() {

    data class Params(
        val idUsuario: Long,
        val page: Long,
        val tipoSolicitacao: TipoSolicitacao
    )

    override suspend fun doWork(params: Params): Long {
        return withContext(dispatchers.io){
            solicitacaoAceiteRepository.updateSolicitacaoAceite(
                idUsuario = params.idUsuario,
                page = params.page,
                tipoSolicitacao = params.tipoSolicitacao
            )
        }
    }
}