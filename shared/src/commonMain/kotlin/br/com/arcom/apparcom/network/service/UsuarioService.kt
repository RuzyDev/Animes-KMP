package br.com.arcom.apparcom.network.service

import br.com.arcom.apparcom.network.models.NetworkUsuarioAppArcom

interface UsuarioService {
      suspend fun login(idUsuario: Long, senha: String): NetworkUsuarioAppArcom?
}
