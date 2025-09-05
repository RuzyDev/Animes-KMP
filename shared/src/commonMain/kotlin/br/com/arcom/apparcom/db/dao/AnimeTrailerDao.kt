package br.com.arcom.apparcom.db.dao

import br.com.arcom.apparcom.network.models.Aired
import br.com.arcom.apparcom.network.models.Details
import br.com.arcom.apparcom.network.models.NetworkAnime
import br.com.arcom.apparcom.network.models.NetworkUsuarioAppAnime
import br.com.arcom.apparcom.network.models.Trailer
import database.UsuarioEntity
import kotlinx.coroutines.flow.Flow

interface AnimeTrailerDao {
    suspend fun insertOrUpdate(animeId: Long, trailer: Trailer)
}