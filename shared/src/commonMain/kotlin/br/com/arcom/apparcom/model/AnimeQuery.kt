package br.com.arcom.apparcom.model

import br.com.arcom.apparcom.util.format.toBoolean
import database.AnimeQueryEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class AnimeQuery(
    val queryType: QueryTypeAnime,
    val queryParam: String,
    val currentPage: Long,
    val lastVisiblePage: Long,
    val hasNextPage: Boolean,
    val totalItems: Long,
    val perPage: Long
)

fun AnimeQueryEntity.asExternalModel() = AnimeQuery(
    queryType = QueryTypeAnime.get(query_type),
    queryParam = query_param,
    currentPage = current_page,
    lastVisiblePage = last_visible_page,
    hasNextPage = has_next_page.toBoolean(),
    totalItems = total_items,
    perPage = per_page
)