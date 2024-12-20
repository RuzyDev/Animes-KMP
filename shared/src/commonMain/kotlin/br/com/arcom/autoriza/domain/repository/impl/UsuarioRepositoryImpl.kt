package br.com.arcom.autoriza.domain.repository.impl

import br.com.arcom.autoriza.db.dao.SolicitacaoAceiteDao
import br.com.arcom.autoriza.domain.repository.UsuarioRepository
import br.com.arcom.autoriza.network.service.UsuarioService

class UsuarioRepositoryImpl(
    private val usuarioService: UsuarioService,
    private val solicitacaoAceiteDao: SolicitacaoAceiteDao
) : UsuarioRepository {

    override suspend fun realizarLogin(idUsuario: Long, senha: String) {
        TODO("Not yet implemented")
    }

}
