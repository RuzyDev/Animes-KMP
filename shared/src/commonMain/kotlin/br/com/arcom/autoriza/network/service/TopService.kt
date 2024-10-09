package br.com.arcom.autoriza.network.service

import br.com.arcom.autoriza.network.models.top.anime.NetworkTopAnime

interface TopService {
   suspend fun getTopAnime(filter: String): NetworkTopAnime
   suspend fun getTopManga(filter: String): NetworkTopAnime
}
