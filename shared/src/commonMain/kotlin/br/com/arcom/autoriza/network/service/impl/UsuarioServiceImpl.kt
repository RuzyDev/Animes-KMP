package br.com.arcom.autoriza.network.service.impl

import br.com.arcom.autoriza.network.models.NetworkLogin
import br.com.arcom.autoriza.network.models.NetworkSolicitacaoAceite
import br.com.arcom.autoriza.network.models.NetworkUsuarioAppArcom
import br.com.arcom.autoriza.network.service.SolicitacaoService
import br.com.arcom.autoriza.network.service.UsuarioService
import br.com.arcom.autoriza.network.util.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class UsuarioServiceImpl(
    private val api: HttpClient
) : UsuarioService {

    override suspend fun login(idUsuario: Long, senha: String): NetworkUsuarioAppArcom? {
        return safeApiCall {
            api.post(urlString = "api/apparcom/v1/atualizar?rotina=login") {
                setBody(NetworkLogin(idUsuario, senha))
                contentType(ContentType.Application.Json)
            }.body()
        }
    }
}