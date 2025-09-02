package br.com.arcom.apparcom.db.dao.impl

import br.com.arcom.apparcom.db.dao.AnimeDao
import br.com.arcom.apparcom.network.models.NetworkAnime
import br.com.arcom.apparcom.util.format.toLong
import database.AnimeQueries
import korlibs.memory.toInt

class AnimeDaoImpl(
    private val animeQueries: AnimeQueries
): AnimeDao {
    override suspend fun insertOrUpdate(anime: NetworkAnime) {
        animeQueries.insertOrUpdate(
            id = anime.malId,
            url = anime.url,
            approved = anime.approved.toLong(),
            title = anime.title,
            title_english = anime.titleEnglish,
            title_japanese = anime.titleJapanese,
            type = anime.type,
            source = anime.source,
            episodes = anime.episodes,
            status = anime.status,
            airing = anime.airing.toLong(),
            duration = anime.duration,
            rating = anime.rating,
            score = anime.score,
            scored_by = anime.scoredBy,
            rank = anime.rank,
            popularity = anime.popularity,
            members = anime.members,
            favorites = anime.favorites,
            synopsis = anime.synopsis,
            background = anime.background,
            season = anime.season,
            year = anime.year,
        )
    }

}