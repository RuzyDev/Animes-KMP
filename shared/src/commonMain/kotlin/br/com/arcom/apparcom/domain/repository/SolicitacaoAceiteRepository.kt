package br.com.arcom.apparcom.domain.repository

import br.com.arcom.apparcom.model.solicitacao.SolicitacaoAceite
import kotlinx.coroutines.flow.Flow

interface SolicitacaoAceiteRepository {

    suspend fun updateSolicitacaoAceite(idUsuario: Long, page: Short)
    suspend fun registrarSolicitacao(solicitacao: SolicitacaoAceite)
    fun observeSolicitacoesAceite(page: Long): Flow<List<SolicitacaoAceite>>
    fun observeSolicitacaoAceite(id: String): Flow<SolicitacaoAceite>
}
