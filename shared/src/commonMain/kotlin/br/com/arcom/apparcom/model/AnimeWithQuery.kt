package br.com.arcom.apparcom.model

import br.com.arcom.apparcom.util.format.toBoolean
import database.SelectAnimesByQuery

data class AnimeWithQuery(
    val query: AnimeQuery,
    val animes: List<Anime>
)

fun SelectAnimesByQuery.asAnimeModel() = Anime(
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

fun SelectAnimesByQuery.asQueryModel() = AnimeQuery(
    queryType = QueryTypeAnime.get(query_type),
    queryParam = query_param,
    currentPage = current_page,
    lastVisiblePage = last_visible_page,
    hasNextPage = has_next_page.toBoolean(),
    totalItems = total_items,
    perPage = per_page,
)