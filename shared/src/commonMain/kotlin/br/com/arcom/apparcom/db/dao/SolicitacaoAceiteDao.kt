package br.com.arcom.apparcom.db.dao

import br.com.arcom.apparcom.db.solicitacao.SolicitacaoAceiteEntity
import br.com.arcom.apparcom.model.solicitacao.TipoSolicitacao
import br.com.arcom.apparcom.network.models.NetworkSolicitacaoAceite
import kotlinx.coroutines.flow.Flow

interface SolicitacaoAceiteDao {
    suspend fun insertOrUpdate(solicitacao: NetworkSolicitacaoAceite)
    fun getAllStream(search: String, filtro: TipoSolicitacao): Flow<List<SolicitacaoAceiteEntity>>
    fun getById(id: String): Flow<SolicitacaoAceiteEntity>
    fun getQtdNaoRespondidas(): Flow<Long>
}