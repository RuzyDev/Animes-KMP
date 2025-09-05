package br.com.arcom.apparcom.db.dao

import br.com.arcom.apparcom.model.QueryTypeAnime
import database.AnimeEntity

interface AnimeQueryCrossRefDao {

    suspend fun insertOrUpdate(
        queryType: QueryTypeAnime,
        queryParam: String,
        page: Long,
        animeId: Long,
        position: Long
    )

    suspend fun deleteByPage(
        queryType: QueryTypeAnime,
        queryParam: String,
        page: Long
    )

    suspend fun getAnimes(
        queryType: String,
        queryParam: String,
        page: Long
    ): List<AnimeEntity>

}