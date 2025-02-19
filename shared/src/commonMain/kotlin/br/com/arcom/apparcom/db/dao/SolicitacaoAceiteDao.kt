package br.com.arcom.apparcom.db.dao

import database.SolicitacaoAceiteEntity
import br.com.arcom.apparcom.model.solicitacao.TipoSolicitacao
import br.com.arcom.apparcom.network.models.NetworkSolicitacaoAceite
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime

interface SolicitacaoAceiteDao {
    suspend fun insertOrUpdate(solicitacao: NetworkSolicitacaoAceite, page: Long)
    suspend fun updateResposta(id: String, status: String, dataResposta: LocalDateTime)
    fun getAllStream(search: String, filtro: TipoSolicitacao, page: Long): Flow<List<SolicitacaoAceiteEntity>>
    fun getById(id: String): Flow<SolicitacaoAceiteEntity>
    fun getQtdNaoRespondidas(): Flow<Long>
    fun deleteByPage(page: Long)
}