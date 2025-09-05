package br.com.arcom.apparcom.db.dao.impl

import br.com.arcom.apparcom.db.dao.AnimeStudioDao
import br.com.arcom.apparcom.network.models.Details
import database.AnimeProducerQueries

class AnimeStudioDaoImpl(
    private val animeProducerQueries: AnimeProducerQueries
): AnimeStudioDao {
    override suspend fun insertOrUpdate(
        animeId: Long,
        studios: List<Details>
    ) {
        studios.forEach { studio ->
            animeProducerQueries.insertOrUpdate(
                anime_id = animeId,
                type = studio.type,
                name = studio.name,
                url = studio.url,
            )
        }
    }
}