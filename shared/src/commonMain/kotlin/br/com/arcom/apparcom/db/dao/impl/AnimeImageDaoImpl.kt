package br.com.arcom.apparcom.db.dao.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import br.com.arcom.apparcom.db.dao.AnimeImageDao
import br.com.arcom.apparcom.db.dao.AnimeQueryDao
import br.com.arcom.apparcom.db.dao.AnimeStudioDao
import br.com.arcom.apparcom.db.dao.AnimeTrailerDao
import br.com.arcom.apparcom.model.QueryTypeAnime
import br.com.arcom.apparcom.network.models.Details
import br.com.arcom.apparcom.network.models.Images
import br.com.arcom.apparcom.network.models.NetworkPagination
import br.com.arcom.apparcom.network.models.Trailer
import br.com.arcom.apparcom.util.format.toLong
import database.AnimeImageQueries
import database.AnimeQueryEntity
import database.AnimeQueryQueries
import database.AnimeTrailerQueries
import database.SelectAnimesByQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class AnimeImageDaoImpl(
    private val animeImageQueries: AnimeImageQueries
): AnimeImageDao {
    override suspend fun insertOrUpdate(
        animeId: Long,
        images: Images
    ) {
        if (images.imageUrl != null) {
            animeImageQueries.insertOrUpdate(
                anime_id = animeId,
                image_url = images.imageUrl,
                small_image_url = images.smallImageUrl,
                large_image_url = images.largeImageUrl,
            )
        }
    }
}