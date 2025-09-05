package br.com.arcom.apparcom.db.dao.impl

import br.com.arcom.apparcom.db.dao.AnimeProducerDao
import br.com.arcom.apparcom.network.models.Details
import database.AnimeProducerQueries

class AnimeProducerDaoImpl(
    private val animeProducerQueries: AnimeProducerQueries
): AnimeProducerDao {
    override suspend fun insertOrUpdate(
        animeId: Long,
        producers: List<Details>
    ) {
        producers.forEach { producer ->
            animeProducerQueries.insertOrUpdate(
                anime_id = animeId,
                type = producer.type,
                name = producer.name,
                url = producer.url,
            )
        }
    }
}