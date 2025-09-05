package br.com.arcom.apparcom.db.dao

import br.com.arcom.apparcom.network.models.NetworkAnime
import br.com.arcom.apparcom.network.models.NetworkUsuarioAppAnime
import database.UsuarioEntity
import kotlinx.coroutines.flow.Flow

interface AnimeDao {
    suspend fun insertOrUpdate(anime: NetworkAnime)
}