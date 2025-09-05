package br.com.arcom.apparcom.network.service.impl

import br.com.arcom.apparcom.model.FilterTopAnimes
import br.com.arcom.apparcom.network.models.NetworkAnime
import br.com.arcom.apparcom.network.models.NetworkAnimeWithPagination
import br.com.arcom.apparcom.network.models.NetworkGenre
import br.com.arcom.apparcom.network.models.NetworkLogin
import br.com.arcom.apparcom.network.models.NetworkPushToken
import br.com.arcom.apparcom.network.models.NetworkUsuarioAppAnime
import br.com.arcom.apparcom.network.service.AnimeService
import br.com.arcom.apparcom.network.service.UsuarioService
import br.com.arcom.apparcom.network.util.bodyOrNull
import br.com.arcom.apparcom.network.util.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AnimeServiceImpl(
    private val api: HttpClient
) : AnimeService {

    override suspend fun getGenres(): List<NetworkGenre> =
        safeApiCall {
            api.get(urlString = "/v4/genres/anime"){
                contentType(ContentType.Application.Json)
            }.body()
        }

    override suspend fun getTopAnimes(
        filter: FilterTopAnimes,
        page: Long
    ): NetworkAnimeWithPagination? =
        safeApiCall {
            api.get(urlString = "/v4/top/anime"){
                filter.filter?.let {
                    parameter("filter", it)
                }
                parameter("page", page)
                contentType(ContentType.Application.Json)
            }.body()
        }
}