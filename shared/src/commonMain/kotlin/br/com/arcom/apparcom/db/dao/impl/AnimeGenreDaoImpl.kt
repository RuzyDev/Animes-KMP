package br.com.arcom.apparcom.db.dao.impl

import br.com.arcom.apparcom.db.dao.AnimeGenreDao
import br.com.arcom.apparcom.network.models.Details
import database.AnimeGenreQueries

class AnimeGenreDaoImpl(
    private val animeGenreQueries: AnimeGenreQueries
): AnimeGenreDao {
    override suspend fun insertOrUpdate(
        animeId: Long,
        genres: List<Details>
    ) {
        genres.forEach { genre ->
            animeGenreQueries.insertOrUpdate(
                anime_id = animeId,
                type = genre.type,
                name = genre.name,
                url = genre.url,
            )
        }
    }
}