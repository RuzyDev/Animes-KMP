package br.com.arcom.apparcom.network.service

import br.com.arcom.apparcom.network.models.NetworkPushToken
import br.com.arcom.apparcom.network.models.NetworkUsuarioAppAnime

interface UsuarioService {
      suspend fun login(idUsuario: Long, senha: String): NetworkUsuarioAppAnime?
      suspend fun enviarToken(token : NetworkPushToken)
}
