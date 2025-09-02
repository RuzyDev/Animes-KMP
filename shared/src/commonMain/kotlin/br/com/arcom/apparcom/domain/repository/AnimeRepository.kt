package br.com.arcom.apparcom.domain.repository

import br.com.arcom.apparcom.model.FilterTopAnimes
import br.com.arcom.apparcom.model.Usuario
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun getTopAnimes(filter: FilterTopAnimes, page: Long)
}
