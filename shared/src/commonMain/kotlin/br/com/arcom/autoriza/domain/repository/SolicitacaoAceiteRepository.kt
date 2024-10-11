package br.com.arcom.autoriza.domain.repository

import br.com.arcom.autoriza.model.solicitacao.SolicitacaoAceite
import kotlinx.coroutines.flow.Flow

interface SolicitacaoAceiteRepository {

    suspend fun updateSolicitacaoAceite(idUsuario: Long)
    fun observeSolicitacaoAceite(page: Long): Flow<List<SolicitacaoAceite>>
}
