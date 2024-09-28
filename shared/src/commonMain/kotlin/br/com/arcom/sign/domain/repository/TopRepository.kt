package br.com.arcom.sign.domain.repository

import br.com.arcom.sign.model.anime.AnimeDetails
import br.com.arcom.sign.model.manga.MangaDetails
import br.com.arcom.sign.model.ranking.TypeRakingAnime
import br.com.arcom.sign.model.ranking.TypeRakingManga
import br.com.arcom.sign.network.models.top.anime.NetworkTopAnime
import kotlinx.coroutines.flow.Flow

interface TopRepository {

    suspend fun updateTopAnimes(type: TypeRakingAnime)
    suspend fun updateTopMangas(type: TypeRakingManga)
    fun observeTopAnimes(type: TypeRakingAnime, page: Long): Flow<List<AnimeDetails>>
    fun observeTopMangas(type: TypeRakingManga, page: Long): Flow<List<MangaDetails>>
    suspend fun getTopAnimes(type: TypeRakingAnime, page: Long): List<AnimeDetails>
    suspend fun getTopMangas(type: TypeRakingManga, page: Long): List<MangaDetails>
}
