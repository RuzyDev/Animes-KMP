package br.com.arcom.autoriza.domain.repository.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import br.com.arcom.autoriza.db.anime.AnimeQueries
import br.com.arcom.autoriza.db.anime.ImageAnimeEntity
import br.com.arcom.autoriza.db.anime.ImageAnimeQueries
import br.com.arcom.autoriza.db.manga.ImageMangaEntity
import br.com.arcom.autoriza.db.manga.ImageMangaQueries
import br.com.arcom.autoriza.db.manga.MangaQueries
import br.com.arcom.autoriza.db.anime.RankingAnimeQueries
import br.com.arcom.autoriza.db.manga.RankingMangaQueries
import br.com.arcom.autoriza.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.autoriza.domain.repository.TopRepository
import br.com.arcom.autoriza.model.anime.AnimeDetails
import br.com.arcom.autoriza.model.anime.toAnimeDetails
import br.com.arcom.autoriza.model.ranking.TypeRakingAnime
import br.com.arcom.autoriza.model.ranking.TypeRakingManga
import br.com.arcom.autoriza.model.anime.toExternalModel
import br.com.arcom.autoriza.model.manga.MangaDetails
import br.com.arcom.autoriza.model.manga.toExternalModel
import br.com.arcom.autoriza.model.manga.toMangaDetails
import br.com.arcom.autoriza.model.solicitacao.SolicitacaoAceite
import br.com.arcom.autoriza.network.service.TopService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SolicitacaoAceiteRepositoryImpl(

) : SolicitacaoAceiteRepository {


    override suspend fun getTopAnimes(type: TypeRakingAnime, page: Long): List<AnimeDetails> =
        animeQueries.getTopAnimes(type.type, page).executeAsList().map { anime ->
            val images = imageAnimeQueries.getByIdAnime(anime.id).executeAsList()
                .map(ImageAnimeEntity::toExternalModel)
            anime.toAnimeDetails(images)
        }


    override suspend fun updateSolicitacaoAceite(idUsuario: Long) {
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

    override fun observeSolicitacaoAceite(page: Long): Flow<List<SolicitacaoAceite>> {
        animeQueries.getTopAnimes(type.type, page).asFlow().mapToList(Dispatchers.IO).map {
            it.map { anime ->
                val images = imageAnimeQueries.getByIdAnime(anime.id).executeAsList()
                    .map(ImageAnimeEntity::toExternalModel)
                anime.toAnimeDetails(images)
            }
        }
    }

}
