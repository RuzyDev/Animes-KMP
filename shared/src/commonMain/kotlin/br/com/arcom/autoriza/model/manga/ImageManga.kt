package br.com.arcom.autoriza.model.manga

import br.com.arcom.autoriza.db.manga.ImageMangaEntity

data class ImageManga(
    val id: Long,
    val url: String,
    val type: String = ""
)

fun ImageMangaEntity.toExternalModel() = ImageManga(
    id = id,
    url = url ?: ""
)