package br.com.arcom.sign.network.models.recomendation

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class AnimeRecomendation (
  @SerialName("pagination" ) var paginationRecomendation : PaginationRecomendation?     = PaginationRecomendation(),
  @SerialName("data"       ) var data       : ArrayList<DataRecomendation> = arrayListOf()
)