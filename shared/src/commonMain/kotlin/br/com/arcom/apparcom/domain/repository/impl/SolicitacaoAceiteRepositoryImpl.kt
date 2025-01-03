package br.com.arcom.apparcom.domain.repository.impl

import br.com.arcom.apparcom.db.dao.SolicitacaoAceiteDao
import br.com.arcom.apparcom.db.solicitacao.SolicitacaoAceiteEntity
import br.com.arcom.apparcom.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.apparcom.model.solicitacao.SolicitacaoAceite
import br.com.arcom.apparcom.model.solicitacao.toExternalModel
import br.com.arcom.apparcom.model.solicitacao.toNetwork
import br.com.arcom.apparcom.network.service.SolicitacaoService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SolicitacaoAceiteRepositoryImpl(
    private val solicitacaoService: SolicitacaoService,
    private val solicitacaoAceiteDao: SolicitacaoAceiteDao
) : SolicitacaoAceiteRepository {

    override suspend fun updateSolicitacaoAceite(idUsuario: Long, page: Short) {
        val solicitacoes = solicitacaoService.buscarSolicitacoes(idUsuario, page) ?: emptyList()
        if (solicitacoes.isNotEmpty()) {
            solicitacoes.forEach {
                solicitacaoAceiteDao.insertOrUpdate(it)
            }
        }
    }

    override suspend fun registrarSolicitacao(solicitacao: SolicitacaoAceite) {
        solicitacaoService.registrarSolicitacao(solicitacao.toNetwork())
    }

    override fun observeSolicitacoesAceite(page: Long): Flow<List<SolicitacaoAceite>> =
        solicitacaoAceiteDao.getAllStream().map {
            it.map(SolicitacaoAceiteEntity::toExternalModel)
        }

    override fun observeSolicitacaoAceite(id: String): Flow<SolicitacaoAceite> =
        solicitacaoAceiteDao.getById(id).map(SolicitacaoAceiteEntity::toExternalModel)

}
