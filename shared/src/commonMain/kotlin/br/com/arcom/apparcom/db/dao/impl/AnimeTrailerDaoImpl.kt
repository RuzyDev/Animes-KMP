package br.com.arcom.apparcom.db.dao.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import br.com.arcom.apparcom.db.dao.AnimeQueryDao
import br.com.arcom.apparcom.db.dao.AnimeStudioDao
import br.com.arcom.apparcom.db.dao.AnimeTrailerDao
import br.com.arcom.apparcom.model.QueryTypeAnime
import br.com.arcom.apparcom.network.models.Details
import br.com.arcom.apparcom.network.models.NetworkPagination
import br.com.arcom.apparcom.network.models.Trailer
import br.com.arcom.apparcom.util.format.toLong
import database.AnimeQueryEntity
import database.AnimeQueryQueries
import database.AnimeTrailerQueries
import database.SelectAnimesByQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class AnimeTrailerDaoImpl(
    private val animeTrailerQueries: AnimeTrailerQueries
): AnimeTrailerDao {
    override suspend fun insertOrUpdate(
        animeId: Long,
        trailer: Trailer
    ) {
        if (trailer.youtubeId != null) {
            animeTrailerQueries.insertOrUpdate(
                anime_id = animeId,
                youtube_id = trailer.youtubeId,
                url = trailer.url,
                embed_url = trailer.embedUrl,
                image_url = trailer.images?.imageUrl,
                small_image_url = trailer.images?.smallImageUrl,
                medium_image_url = trailer.images?.mediumImageUrl,
                large_image_url = trailer.images?.largeImageUrl,
                maximum_image_url = trailer.images?.maximumImageUrl
            )
        }
    }
}