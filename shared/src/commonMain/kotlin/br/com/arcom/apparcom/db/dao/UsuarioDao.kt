package br.com.arcom.apparcom.db.dao

import br.com.arcom.apparcom.network.models.NetworkUsuarioAppAnime
import database.UsuarioEntity
import kotlinx.coroutines.flow.Flow

interface UsuarioDao {
    suspend fun insertOrUpdate(usuario: NetworkUsuarioAppAnime)
    fun getStream(): Flow<UsuarioEntity?>
    fun get(): UsuarioEntity?
}