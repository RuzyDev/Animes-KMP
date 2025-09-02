package br.com.arcom.apparcom.domain.repository.impl

import br.com.arcom.apparcom.data.datastore.AppArcomStorage
import br.com.arcom.apparcom.data.datastore.Keys
import br.com.arcom.apparcom.data.preferences.KeysPreferences
import br.com.arcom.apparcom.data.preferences.PreferencesManager
import br.com.arcom.apparcom.db.dao.AnimeDao
import br.com.arcom.apparcom.db.dao.AnimeQueryCrossRefDao
import br.com.arcom.apparcom.db.dao.AnimeQueryDao
import br.com.arcom.apparcom.db.dao.UsuarioDao
import br.com.arcom.apparcom.domain.repository.AnimeRepository
import br.com.arcom.apparcom.domain.repository.UsuarioRepository
import br.com.arcom.apparcom.model.FilterTopAnimes
import br.com.arcom.apparcom.model.QueryTypeAnime
import br.com.arcom.apparcom.model.Usuario
import br.com.arcom.apparcom.model.toExternalModel
import br.com.arcom.apparcom.network.models.NetworkPushToken
import br.com.arcom.apparcom.network.service.AnimeService
import br.com.arcom.apparcom.network.service.UsuarioService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnimeRepositoryImpl(
    private val animeService: AnimeService,
    private val animeDao: AnimeDao,
    private val animeQueryDao: AnimeQueryDao,
    private val animeQueryCrossRefDao: AnimeQueryCrossRefDao,
    private val preferencesManager: PreferencesManager
) : AnimeRepository {
    override suspend fun getTopAnimes(
        filter: FilterTopAnimes,
        page: Long
    ) {
        val animes = animeService.getTopAnimes(filter, page)
        if (animes?.pagination != null){
            animeQueryDao.insertOrUpdate(animes.pagination, QueryTypeAnime.TOP, filter.filter)
            animeQueryCrossRefDao.deleteByPage(QueryTypeAnime.TOP, filter.filter, page)
            animes.animes?.forEach { anime ->
                if (anime.malId != null) {
                    animeDao.insertOrUpdate(anime)
                    animeQueryCrossRefDao.insertOrUpdate(QueryTypeAnime.TOP, filter.filter, page, anime.malId)
                }
            }
        }
    }

    fun observeAnimes(){
        animeQueryDao.
    }
}
