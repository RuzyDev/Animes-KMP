package br.com.arcom.sign.network.models.top.manga

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class NetworkTopMangaAuthors (

  @SerialName("mal_id" ) var malId : Int?    = null,
  @SerialName("type"   ) var type  : String? = null,
  @SerialName("name"   ) var name  : String? = null,
  @SerialName("url"    ) var url   : String? = null

)