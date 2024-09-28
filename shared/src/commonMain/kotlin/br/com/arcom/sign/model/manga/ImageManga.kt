package br.com.arcom.sign.model.manga

import br.com.arcom.sign.db.manga.ImageMangaEntity

data class ImageManga(
    val id: Long,
    val url: String,
    val type: String = ""
)

fun ImageMangaEntity.toExternalModel() = ImageManga(
    id = id,
    url = url ?: ""
)