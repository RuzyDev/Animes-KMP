package br.com.arcom.autoriza.db.dao

import br.com.arcom.autoriza.db.solicitacao.SolicitacaoAceiteEntity
import br.com.arcom.autoriza.db.solicitacao.UsuarioEntity
import br.com.arcom.autoriza.model.Usuario
import br.com.arcom.autoriza.network.models.NetworkSolicitacaoAceite
import br.com.arcom.autoriza.network.models.NetworkUsuarioAppArcom
import kotlinx.coroutines.flow.Flow

interface UsuarioDao {
    suspend fun insertOrUpdate(usuario: NetworkUsuarioAppArcom)
    fun get(): Flow<UsuarioEntity?>
}