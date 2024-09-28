package br.com.arcom.sign.network.service

import br.com.arcom.sign.network.models.top.anime.NetworkTopAnime

interface TopService {
   suspend fun getTopAnime(filter: String): NetworkTopAnime
   suspend fun getTopManga(filter: String): NetworkTopAnime
}
