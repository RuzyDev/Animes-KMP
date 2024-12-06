package br.com.arcom.autoriza.db.dao

import br.com.arcom.autoriza.db.solicitacao.SolicitacaoAceiteEntity
import br.com.arcom.autoriza.network.models.NetworkSolicitacaoAceite
import kotlinx.coroutines.flow.Flow

interface SolicitacaoAceiteDao {
    suspend fun insertOrUpdate(solicitacao: NetworkSolicitacaoAceite)
    fun getAllStream(): Flow<List<SolicitacaoAceiteEntity>>
    fun getById(id: String): Flow<SolicitacaoAceiteEntity>
}