package br.com.arcom.sign.network.service.impl

import br.com.arcom.sign.network.models.top.anime.NetworkTopAnime
import br.com.arcom.sign.network.service.TopService
import br.com.arcom.sign.network.util.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType

class TopServiceImpl(
    private val api: HttpClient
) : TopService {

    override suspend fun getTopAnime(filter: String): NetworkTopAnime =
        safeApiCall {
            api.get(urlString = "top/anime"){
                parameter("filter", filter)
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