package br.com.arcom.apparcom.domain.repository.impl

import br.com.arcom.apparcom.db.dao.SolicitacaoAceiteDao
import br.com.arcom.apparcom.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.apparcom.model.solicitacao.SolicitacaoAceite
import br.com.arcom.apparcom.model.solicitacao.toExternalModel
import br.com.arcom.apparcom.model.solicitacao.toNetwork
import br.com.arcom.apparcom.network.service.SolicitacaoService
import database.SolicitacaoAceiteEntity
import br.com.arcom.apparcom.model.solicitacao.TipoSolicitacao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SolicitacaoAceiteRepositoryImpl(
    private val solicitacaoService: SolicitacaoService,
    private val solicitacaoAceiteDao: SolicitacaoAceiteDao
) : SolicitacaoAceiteRepository {

    override suspend fun updateSolicitacaoAceite(idUsuario: Long, page: Long) : Long {
        val result = solicitacaoService.buscarSolicitacoes(idUsuario, page)
        val solicitacoes = result?.solicitacoes ?: emptyList()
        if (solicitacoes.isNotEmpty()) {
            solicitacoes.forEach {
                solicitacaoAceiteDao.insertOrUpdate(it, page)
            }
        }
        return result?.totalPaginas ?: 0
    }

    override suspend fun registrarSolicitacao(solicitacao: SolicitacaoAceite, idUsuario: Long) {
        solicitacaoService.registrarSolicitacao(solicitacao.toNetwork(), idUsuario)
        solicitacao.dataResposta?.let { resposta ->
            solicitacaoAceiteDao.updateResposta(solicitacao.id, solicitacao.status.value, resposta)
        }
    }

    override fun observeSolicitacoesAceite(page: Long, search: String, filtro: TipoSolicitacao): Flow<List<SolicitacaoAceite>> =
        solicitacaoAceiteDao.getAllStream(search, filtro, page).map {
            it.map(SolicitacaoAceiteEntity::toExternalModel)
        }

    override fun observeSolicitacaoAceite(id: String): Flow<SolicitacaoAceite> =
        solicitacaoAceiteDao.getById(id).map(SolicitacaoAceiteEntity::toExternalModel)

    override fun observeQtdSolicitacoesPendentes(): Flow<Long> =
        solicitacaoAceiteDao.getQtdNaoRespondidas()

}
