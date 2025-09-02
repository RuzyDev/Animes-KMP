package br.com.arcom.apparcom.db.dao

import br.com.arcom.apparcom.model.QueryTypeAnime
import br.com.arcom.apparcom.network.models.NetworkPagination
import br.com.arcom.apparcom.network.models.NetworkUsuarioAppArcom
import database.UsuarioEntity
import kotlinx.coroutines.flow.Flow

interface AnimeQueryCrossRefDao {

    suspend fun insertOrUpdate(
        queryType: QueryTypeAnime,
        queryParam: String?,
        page: Long,
        animeId: Long
    )

    suspend fun deleteByPage(
        queryType: QueryTypeAnime,
        queryParam: String?,
        page: Long
    )

}