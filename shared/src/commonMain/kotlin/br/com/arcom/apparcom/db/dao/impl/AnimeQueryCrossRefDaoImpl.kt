package br.com.arcom.apparcom.db.dao.impl

import br.com.arcom.apparcom.db.dao.AnimeQueryCrossRefDao
import br.com.arcom.apparcom.model.QueryTypeAnime
import database.AnimeEntity
import database.AnimeQueryCrossRefQueries

class AnimeQueryCrossRefDaoImpl(
    private val animeQueryCrossRefQueries: AnimeQueryCrossRefQueries
): AnimeQueryCrossRefDao {
    override suspend fun insertOrUpdate(
        queryType: QueryTypeAnime,
        queryParam: String,
        page: Long,
        animeId: Long,
        position: Long
    ) {
        animeQueryCrossRefQueries.insertOrUpdate(
            query_type = queryType.type,
            query_param = queryParam,
            page = page,
            anime_id = animeId,
            position = position
        )
    }

    override suspend fun deleteByPage(
        queryType: QueryTypeAnime,
        queryParam: String,
        page: Long
    ) {
        animeQueryCrossRefQueries.deleteByPage(queryType.type, queryParam, page)
    }

    override suspend fun getAnimes(
        queryType: String,
        queryParam: String,
        page: Long
    ): List<AnimeEntity> = animeQueryCrossRefQueries.getAnimes(
        queryType,
        queryParam,
        page
    ).executeAsList()
}