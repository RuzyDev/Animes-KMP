package br.com.arcom.apparcom.domain.repository.impl

import br.com.arcom.apparcom.data.preferences.PreferencesManager
import br.com.arcom.apparcom.db.dao.AnimeDao
import br.com.arcom.apparcom.db.dao.AnimeQueryCrossRefDao
import br.com.arcom.apparcom.db.dao.AnimeQueryDao
import br.com.arcom.apparcom.db.dao.AnimeAiredDao
import br.com.arcom.apparcom.db.dao.AnimeGenreDao
import br.com.arcom.apparcom.db.dao.AnimeImageDao
import br.com.arcom.apparcom.db.dao.AnimeProducerDao
import br.com.arcom.apparcom.db.dao.AnimeStudioDao
import br.com.arcom.apparcom.db.dao.AnimeTrailerDao
import br.com.arcom.apparcom.domain.repository.AnimeRepository
import br.com.arcom.apparcom.model.AnimeWithQuery
import br.com.arcom.apparcom.model.FilterTopAnimes
import br.com.arcom.apparcom.model.QueryTypeAnime
import br.com.arcom.apparcom.model.asAnimeModel
import br.com.arcom.apparcom.model.asExternalModel
import br.com.arcom.apparcom.model.asQueryModel
import br.com.arcom.apparcom.network.service.AnimeService
import br.com.arcom.apparcom.util.log.CommonLoggerImpl
import korlibs.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

class AnimeRepositoryImpl(
    private val animeService: AnimeService,
    private val animeDao: AnimeDao,
    private val animeQueryDao: AnimeQueryDao,
    private val animeQueryCrossRefDao: AnimeQueryCrossRefDao,
    private val animeAiredDao : AnimeAiredDao,
    private val animeGenreDao : AnimeGenreDao,
    private val animeImageDao : AnimeImageDao,
    private val animeProducerDao : AnimeProducerDao,
    private val animeStudioDao : AnimeStudioDao,
    private val animeTrailerDao : AnimeTrailerDao,
    private val preferencesManager: PreferencesManager
) : AnimeRepository {
    override suspend fun updateTopAnimes(
        filter: FilterTopAnimes,
        page: Long
    ) {
        val animes = animeService.getTopAnimes(filter, page)
        if (animes?.pagination != null) {
            animeQueryDao.insertOrUpdate(animes.pagination, QueryTypeAnime.TOP, filter.param)
            animeQueryCrossRefDao.deleteByPage(QueryTypeAnime.TOP, filter.param, page)
            animes.animes?.forEachIndexed { index, anime ->
                if (anime.malId != null) {
                    animeDao.insertOrUpdate(anime)
                    animeQueryCrossRefDao.insertOrUpdate(
                        QueryTypeAnime.TOP,
                        filter.param,
                        page,
                        anime.malId,
                        index.toLong()
                    )
                    animeAiredDao.insertOrUpdate(anime.malId, anime.aired)
                    animeGenreDao.insertOrUpdate(anime.malId, anime.genres)
                    animeProducerDao.insertOrUpdate(anime.malId, anime.producers)
                    animeStudioDao.insertOrUpdate(anime.malId, anime.studios)
                    animeTrailerDao.insertOrUpdate(anime.malId, anime.trailer)
                }
            }
        }
    }

    override fun observeAnimes(
        type: QueryTypeAnime,
        param: String
    ): Flow<AnimeWithQuery?> {
        return animeQueryDao.observeAnimes(type, param)
            .mapNotNull { queries ->
                queries.firstOrNull()?.let { firstQuery ->
                    val queryModel = firstQuery.asQueryModel()
                    val animes = queries.map { it.asAnimeModel() }
                    AnimeWithQuery(queryModel, animes)
                }
            }
    }
}
