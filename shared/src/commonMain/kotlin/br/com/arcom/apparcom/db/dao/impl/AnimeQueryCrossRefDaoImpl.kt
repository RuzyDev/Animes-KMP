package br.com.arcom.apparcom.db.dao.impl

import br.com.arcom.apparcom.db.dao.AnimeDao
import br.com.arcom.apparcom.db.dao.AnimeQueryCrossRefDao
import br.com.arcom.apparcom.model.QueryTypeAnime
import database.AnimeQueries
import database.AnimeQueryCrossRefQueries
import database.AnimeQueryQueries

class AnimeQueryCrossRefDaoImpl(
    private val animeQueryCrossRefQueries: AnimeQueryCrossRefQueries
): AnimeQueryCrossRefDao {
    override suspend fun insertOrUpdate(
        queryType: QueryTypeAnime,
        queryParam: String?,
        page: Long,
        animeId: Long
    ) {
        animeQueryCrossRefQueries.insertOrUpdate(
            query_type = queryType.type,
            query_param = queryParam,
            page = page,
            anime_id = animeId
        )
    }

    override suspend fun deleteByPage(
        queryType: QueryTypeAnime,
        queryParam: String?,
        page: Long
    ) {
        animeQueryCrossRefQueries.deleteByPage(queryType.type, queryParam, page)
    }
}