package br.com.arcom.autoriza.network.service

import br.com.arcom.autoriza.network.models.NetworkSolicitacaoAceite
import br.com.arcom.autoriza.network.models.NetworkUsuarioAppArcom

interface UsuarioService {
      suspend fun login(idUsuario: Long, senha: String): NetworkUsuarioAppArcom?
}
