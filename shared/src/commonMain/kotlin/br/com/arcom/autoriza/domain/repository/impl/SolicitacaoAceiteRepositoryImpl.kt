package br.com.arcom.autoriza.domain.repository.impl

import br.com.arcom.autoriza.db.dao.SolicitacaoAceiteDao
import br.com.arcom.autoriza.db.solicitacao.SolicitacaoAceiteEntity
import br.com.arcom.autoriza.db.solicitacao.SolicitacaoAceiteQueries
import br.com.arcom.autoriza.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.autoriza.model.solicitacao.SolicitacaoAceite
import br.com.arcom.autoriza.network.service.SolicitacaoService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SolicitacaoAceiteRepositoryImpl(
    private val solicitacaoService: SolicitacaoService,
    private val solicitacaoAceiteDao: SolicitacaoAceiteDao
) : SolicitacaoAceiteRepository {

    override suspend fun updateSolicitacaoAceite(idUsuario: Long, page: Short) {
        val solicitacoes = solicitacaoService.buscarSolicitacoes(idUsuario, page) ?: emptyList()
        if (solicitacoes.isNotEmpty()){
            solicitacoes.forEach {
                solicitacaoAceiteQueries.insertOrUpdate()
            }
        }
    }

    override fun observeSolicitacaoAceite(page: Long): Flow<List<SolicitacaoAceite>> {
        solicitacaoAceiteDao.getAllStream()
        return flow {  }
    }

}
