package br.com.arcom.autoriza.domain.repository

import br.com.arcom.autoriza.model.anime.AnimeDetails
import br.com.arcom.autoriza.model.manga.MangaDetails
import br.com.arcom.autoriza.model.ranking.TypeRakingAnime
import br.com.arcom.autoriza.model.ranking.TypeRakingManga
import kotlinx.coroutines.flow.Flow

interface TopRepository {

    suspend fun updateTopAnimes(type: TypeRakingAnime)
    suspend fun updateTopMangas(type: TypeRakingManga)
    fun observeTopAnimes(type: TypeRakingAnime, page: Long): Flow<List<AnimeDetails>>
    fun observeTopMangas(type: TypeRakingManga, page: Long): Flow<List<MangaDetails>>
    suspend fun getTopAnimes(type: TypeRakingAnime, page: Long): List<AnimeDetails>
    suspend fun getTopMangas(type: TypeRakingManga, page: Long): List<MangaDetails>
}
