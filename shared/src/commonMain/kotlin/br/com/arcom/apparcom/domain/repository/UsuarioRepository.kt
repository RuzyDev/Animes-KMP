package br.com.arcom.apparcom.domain.repository

import br.com.arcom.apparcom.model.Usuario
import kotlinx.coroutines.flow.Flow

interface UsuarioRepository {
    suspend fun realizarLogin(idUsuario: Long, senha: String)
    fun getUsuarioStream(): Flow<Usuario?>
    fun getUsuario(): Usuario?
}
