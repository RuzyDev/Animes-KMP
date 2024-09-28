package br.com.arcom.sign.domain.repository.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import br.com.arcom.sign.db.anime.AnimeQueries
import br.com.arcom.sign.db.anime.ImageAnimeEntity
import br.com.arcom.sign.db.anime.ImageAnimeQueries
import br.com.arcom.sign.db.manga.ImageMangaEntity
import br.com.arcom.sign.db.manga.ImageMangaQueries
import br.com.arcom.sign.db.manga.MangaQueries
import br.com.arcom.sign.db.anime.RankingAnimeQueries
import br.com.arcom.sign.db.manga.RankingMangaQueries
import br.com.arcom.sign.domain.repository.TopRepository
import br.com.arcom.sign.model.anime.AnimeDetails
import br.com.arcom.sign.model.anime.toAnimeDetails
import br.com.arcom.sign.model.ranking.TypeRakingAnime
import br.com.arcom.sign.model.ranking.TypeRakingManga
import br.com.arcom.sign.model.anime.toExternalModel
import br.com.arcom.sign.model.manga.MangaDetails
import br.com.arcom.sign.model.manga.toExternalModel
import br.com.arcom.sign.model.manga.toMangaDetails
import br.com.arcom.sign.network.service.TopService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TopRepositoryImpl(
    private val topService: TopService,
    private val animeQueries: AnimeQueries,
    private val mangaQueries: MangaQueries,
    private val imageMangaQueries: ImageMangaQueries,
    private val imageAnimeQueries: ImageAnimeQueries,
    private val rankingAnimeQueries: RankingAnimeQueries,
    private val rankingMangaQueries: RankingMangaQueries
) : TopRepository {

    override suspend fun updateTopAnimes(type: TypeRakingAnime) {
        val animes = topService.getTopAnime(type.type)
        val entity = animes.data
        entity.forEach {
            rankingAnimeQueries.insertOrUpdate(it.malId, type.type, it.rank)
            animeQueries.insertOrUpdate(id = it.malId, title = it.title)
            imageAnimeQueries.insertOrUpdate(
                id_anime = it.malId,
                url = it.networkAnimeImagesTop?.jpg?.imageUrl
            )
        }
    }

    override suspend fun updateTopMangas(type: TypeRakingManga) {
        val mangas = topService.getTopAnime(type.type)
        val entity = mangas.data
        entity.forEach {
            rankingMangaQueries.insertOrUpdate(it.malId, type.type, it.rank)
            mangaQueries.insertOrUpdate(id = it.malId, title = it.title)
            imageMangaQueries.insertOrUpdate(
                id_manga = it.malId,
                url = it.networkAnimeImagesTop?.jpg?.imageUrl
            )
        }
    }

    override suspend fun getTopAnimes(type: TypeRakingAnime, page: Long): List<AnimeDetails> =
        animeQueries.getTopAnimes(type.type, page).executeAsList().map { anime ->
            val images = imageAnimeQueries.getByIdAnime(anime.id).executeAsList()
                .map(ImageAnimeEntity::toExternalModel)
            anime.toAnimeDetails(images)
        }

    override suspend fun getTopMangas(type: TypeRakingManga, page: Long): List<MangaDetails> =
        mangaQueries.getTopMangas(type.type, page).executeAsList().map { manga ->
            val images = imageMangaQueries.getByIdManga(manga.id).executeAsList()
                .map(ImageMangaEntity::toExternalModel)
            manga.toMangaDetails(images)
        }

    override fun observeTopAnimes(type: TypeRakingAnime, page: Long): Flow<List<AnimeDetails>> =
        animeQueries.getTopAnimes(type.type, page).asFlow().mapToList(Dispatchers.IO).map {
            it.map { anime ->
                val images = imageAnimeQueries.getByIdAnime(anime.id).executeAsList()
                    .map(ImageAnimeEntity::toExternalModel)
                anime.toAnimeDetails(images)
            }
        }

    override fun observeTopMangas(type: TypeRakingManga, page: Long): Flow<List<MangaDetails>> =
        mangaQueries.getTopMangas(type.type, page).asFlow().mapToList(Dispatchers.IO).map {
            it.map { manga ->
                val images = imageMangaQueries.getByIdManga(manga.id).executeAsList()
                    .map(ImageMangaEntity::toExternalModel)
                manga.toMangaDetails(images)
            }
        }

}
