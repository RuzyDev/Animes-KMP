package br.com.arcom.apparcom.db.dao

import br.com.arcom.apparcom.network.models.NetworkUsuarioAppArcom
import br.com.arcom.apparcom.db.solicitacao.UsuarioEntity
import kotlinx.coroutines.flow.Flow

interface UsuarioDao {
    suspend fun insertOrUpdate(usuario: NetworkUsuarioAppArcom)
    fun get(): Flow<UsuarioEntity?>
}