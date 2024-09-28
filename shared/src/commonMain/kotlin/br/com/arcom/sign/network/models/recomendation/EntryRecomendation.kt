package br.com.arcom.sign.network.models.recomendation

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class EntryRecomendation (

  @SerialName("mal_id" ) var malId  : Int?    = null,
  @SerialName("url"    ) var url    : String? = null,
  @SerialName("images" ) var imagesRecomendation : ImagesRecomendation? = ImagesRecomendation(),
  @SerialName("title"  ) var title  : String? = null

)