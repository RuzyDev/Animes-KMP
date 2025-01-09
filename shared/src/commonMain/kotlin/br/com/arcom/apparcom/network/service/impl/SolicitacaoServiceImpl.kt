package br.com.arcom.apparcom.network.service.impl

import br.com.arcom.apparcom.network.models.NetworkSolicitacaoAceite
import br.com.arcom.apparcom.network.service.SolicitacaoService
import br.com.arcom.apparcom.network.util.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class SolicitacaoServiceImpl(
    private val api: HttpClient
) : SolicitacaoService {

    override suspend fun buscarSolicitacoes(idUsuario: Long, nroPagina: Short): List<NetworkSolicitacaoAceite>? =
        safeApiCall {
            api.get(urlString = "/api/apparcom/v1/buscar?rotina=buscar-solicitacoes"){
                parameter("idUsuario", idUsuario)
                parameter("nroPagina", nroPagina)
                contentType(ContentType.Application.Json)
            }.body()
        }

    override suspend fun registrarSolicitacao(solicitacao: NetworkSolicitacaoAceite, idUsuario: Long){
        safeApiCall<Unit> {
            api.post(urlString = "api/apparcom/v1/atualizar?rotina=registrar-solicitacao") {
                parameter("idUsuario", idUsuario)
                setBody(solicitacao)
                contentType(ContentType.Application.Json)
            }.body()
        }
    }
}