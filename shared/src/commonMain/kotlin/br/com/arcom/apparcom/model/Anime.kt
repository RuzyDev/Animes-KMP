package br.com.arcom.apparcom.model

import br.com.arcom.apparcom.util.format.toBoolean
import database.AnimeEntity

data class Anime(
    val id: Long,
    val url: String? = null,
    val approved: Boolean? = null,
    val title: String? = null,
    val titleEnglish: String? = null,
    val titleJapanese: String? = null,
    val type: String? = null,
    val source: String? = null,
    val episodes: Long? = null,
    val status: String? = null,
    val airing: Boolean? = null,
    val duration: String? = null,
    val rating: String? = null,
    val score: Double? = null,
    val scoredBy: Long? = null,
    val rank: Long? = null,
    val popularity: Long? = null,
    val members: Long? = null,
    val favorites: Long? = null,
    val synopsis: String? = null,
    val background: String? = null,
    val season: String? = null,
    val year: String? = null,
    val image: String? = null,
    val imageSmall: String? = null,
    val imageLarge: String? = null
)

fun AnimeEntity.asExternalModel() = Anime(
    id = id,
    url = url,
    approved = approved.toBoolean(),
    title = title,
    titleEnglish = title_english,
    titleJapanese = title_japanese,
    type = type,
    source = source,
    episodes = episodes,
    status = status,
    airing = airing.toBoolean(),
    duration = duration,
    rating = rating,
    score = score,
    scoredBy = scored_by,
    rank = rank,
    popularity = popularity,
    members = members,
    favorites = favorites,
    synopsis = synopsis,
    background = background,
    season = season,
    year = year,
    image = image,
    imageSmall = image_small,
    imageLarge = image_large
)