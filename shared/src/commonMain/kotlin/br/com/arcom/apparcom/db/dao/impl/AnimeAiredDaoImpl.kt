package br.com.arcom.apparcom.db.dao.impl

import br.com.arcom.apparcom.db.dao.AnimeAiredDao
import br.com.arcom.apparcom.network.models.Aired
import database.AnimeAiredQueries

class AnimeAiredDaoImpl(
    private val animeAiredQueries: AnimeAiredQueries
): AnimeAiredDao {
    override suspend fun insertOrUpdate(
        animeId: Long,
        aired: Aired
    ) {
        animeAiredQueries.insertOrUpdate(
            anime_id = animeId,
            from_date = aired.from,
            to_date = aired.to,
            string = aired.string
        )
    }
}