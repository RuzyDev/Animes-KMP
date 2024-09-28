package br.com.arcom.sign.model.anime

import br.com.arcom.sign.db.anime.GetTopAnimes
import br.com.arcom.sign.model.ranking.TypeRakingAnime

data class AnimeDetails(
    val id: Long,
    val title: String,
    val rank: Long?,
    val rank_type: TypeRakingAnime?,
    val images: List<ImageAnime> = emptyList()
)


fun GetTopAnimes.toAnimeDetails(listImages: List<ImageAnime>) =
    AnimeDetails(
        id = id,
        title = title ?: "Not found",
        images = listImages,
        rank = ranking_value,
        rank_type = TypeRakingAnime.getType(ranking_type)
    )