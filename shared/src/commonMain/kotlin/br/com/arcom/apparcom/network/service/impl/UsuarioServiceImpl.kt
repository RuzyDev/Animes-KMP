package br.com.arcom.apparcom.network.service.impl

import br.com.arcom.apparcom.network.models.NetworkLogin
import br.com.arcom.apparcom.network.models.NetworkPushToken
import br.com.arcom.apparcom.network.models.NetworkUsuarioAppAnime
import br.com.arcom.apparcom.network.service.UsuarioService
import br.com.arcom.apparcom.network.util.bodyOrNull
import br.com.arcom.apparcom.network.util.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class UsuarioServiceImpl(
    private val api: HttpClient
) : UsuarioService {

    override suspend fun login(idUsuario: Long, senha: String): NetworkUsuarioAppAnime? {
        return safeApiCall {
            api.post(urlString = "api/apparcom/v1/login") {
                setBody(NetworkLogin(idUsuario, senha))
                contentType(ContentType.Application.Json)
            }.bodyOrNull()
        }
    }

    override suspend fun enviarToken(token: NetworkPushToken) {
        return safeApiCall {
            api.post(urlString = "api/ivendas/v1/setor?rotina=registra-push-token") {
                setBody(token)
                contentType(ContentType.Application.Json)
            }.body()
        }
    }
}