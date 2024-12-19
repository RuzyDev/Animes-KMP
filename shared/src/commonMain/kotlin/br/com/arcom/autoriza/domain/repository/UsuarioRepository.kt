package br.com.arcom.autoriza.domain.repository

import br.com.arcom.autoriza.model.solicitacao.SolicitacaoAceite
import kotlinx.coroutines.flow.Flow

interface UsuarioRepository {

    suspend fun updateSolicitacaoAceite(idUsuario: Long, page: Short)
    suspend fun registrarSolicitacao(solicitacao: SolicitacaoAceite)
    fun observeSolicitacoesAceite(page: Long): Flow<List<SolicitacaoAceite>>
    fun observeSolicitacaoAceite(id: String): Flow<SolicitacaoAceite>
}
