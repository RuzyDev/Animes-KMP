package br.com.arcom.autoriza.network.service.impl

import br.com.arcom.autoriza.network.models.top.anime.NetworkTopAnime
import br.com.arcom.autoriza.network.service.TopService
import br.com.arcom.autoriza.network.util.safeApiCall
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
) : TopService {

    override suspend fun getTopAnime(filter: String): NetworkTopAnime =
        safeApiCall {
            api.post(urlString = "api/ivendas/autoriza"){
                parameter()
                contentType(ContentType.Application.Json)
            }.body()
        }

    override suspend fun getTopManga(filter: String): NetworkTopAnime =
        safeApiCall {
            api.get(urlString = "top/manga"){
                parameter("filter", filter)
                contentType(ContentType.Application.Json)
            }.body()
        }
}