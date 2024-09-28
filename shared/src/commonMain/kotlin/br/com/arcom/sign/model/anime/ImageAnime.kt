package br.com.arcom.sign.model.anime

import br.com.arcom.sign.db.anime.ImageAnimeEntity

data class ImageAnime(
    val id: Long,
    val url: String,
    val type: String = ""
)

fun ImageAnimeEntity.toExternalModel() = ImageAnime(
    id = id,
    url = url ?: ""
)