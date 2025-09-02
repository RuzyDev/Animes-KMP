package br.com.arcom.apparcom.db.dao.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import br.com.arcom.apparcom.db.dao.AnimeDao
import br.com.arcom.apparcom.db.dao.AnimeQueryDao
import br.com.arcom.apparcom.model.FilterTopAnimes
import br.com.arcom.apparcom.model.QueryTypeAnime
import br.com.arcom.apparcom.network.models.NetworkPagination
import br.com.arcom.apparcom.util.format.toLong
import database.AnimeQueries
import database.AnimeQueryEntity
import database.AnimeQueryQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class AnimeQueryDaoImpl(
    private val animeQueryQueries: AnimeQueryQueries
): AnimeQueryDao {
    override suspend fun insertOrUpdate(
        pagination: NetworkPagination,
        queryType: QueryTypeAnime,
        queryParam: String
    ) {
        animeQueryQueries.insertOrUpdate(
            query_type = queryType.type,
            query_param = queryParam,
            current_page = pagination.currentPage ?: 0L,
            last_visible_page = pagination.lastVisiblePage ?: 0L,
            has_next_page = pagination.hasNextPage.toLong(),
            total_items = pagination.items?.total ?: 0L,
            per_page = pagination.items?.perPage ?: 0L
        )
    }

    fun observeAnimeQuery(type: QueryTypeAnime, filter: String?): Flow<AnimeQueryEntity?> {
        return animeQueryQueries.getQuery(type.type, filter).asFlow().mapToOne(Dispatchers.IO)
    }
}