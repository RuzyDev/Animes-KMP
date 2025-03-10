package br.com.arcom.apparcom.domain.repository

import br.com.arcom.apparcom.model.solicitacao.SolicitacaoAceite
import br.com.arcom.apparcom.model.solicitacao.TipoSolicitacao
import kotlinx.coroutines.flow.Flow

interface SolicitacaoAceiteRepository {

    // Retorna o total de páginas de solicitações
    suspend fun updateSolicitacaoAceite(idUsuario: Long, page: Long, tipoSolicitacao: TipoSolicitacao): Long
    suspend fun registrarSolicitacao(solicitacao: SolicitacaoAceite, idUsuario: Long)
    fun observeSolicitacoesAceite(page: Long, search: String, filtro: TipoSolicitacao): Flow<List<SolicitacaoAceite>>
    fun observeSolicitacaoAceite(id: String): Flow<SolicitacaoAceite>
    fun observeQtdSolicitacoesPendentes(): Flow<Long>
}
