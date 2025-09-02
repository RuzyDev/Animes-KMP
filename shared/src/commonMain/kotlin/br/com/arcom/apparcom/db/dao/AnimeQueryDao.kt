package br.com.arcom.apparcom.db.dao

import br.com.arcom.apparcom.model.QueryTypeAnime
import br.com.arcom.apparcom.network.models.NetworkAnime
import br.com.arcom.apparcom.network.models.NetworkPagination
import br.com.arcom.apparcom.network.models.NetworkUsuarioAppArcom
import database.UsuarioEntity
import kotlinx.coroutines.flow.Flow

interface AnimeQueryDao {
    suspend fun insertOrUpdate(
        pagination: NetworkPagination,
        queryType: QueryTypeAnime,
        queryParam: String
    )
}