package br.com.arcom.autoriza.domain.repository

import br.com.arcom.autoriza.model.solicitacao.SolicitacaoAceite
import kotlinx.coroutines.flow.Flow

interface UsuarioRepository {
    suspend fun realizarLogin(idUsuario: Long, senha: String)
}
