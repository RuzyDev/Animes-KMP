package br.com.arcom.apparcom.domain.repository

import br.com.arcom.apparcom.model.AnimeWithQuery
import br.com.arcom.apparcom.model.FilterTopAnimes
import br.com.arcom.apparcom.model.QueryTypeAnime
import br.com.arcom.apparcom.model.Usuario
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun updateTopAnimes(filter: FilterTopAnimes, page: Long)
    fun observeAnimes(
        type: QueryTypeAnime,
        param: String
    ): Flow<AnimeWithQuery?>
}
