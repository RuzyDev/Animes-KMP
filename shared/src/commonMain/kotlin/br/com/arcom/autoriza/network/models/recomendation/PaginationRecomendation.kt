package br.com.arcom.autoriza.network.models.recomendation

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class PaginationRecomendation (

  @SerialName("last_visible_page" ) var lastVisiblePage : Int?     = null,
  @SerialName("has_next_page"     ) var hasNextPage     : Boolean? = null

)