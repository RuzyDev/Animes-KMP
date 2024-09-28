package br.com.arcom.sign.network.models.top.manga

import br.com.arcom.sign.network.models.top.NetworkTopProp
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class NetworkTopMangaPublished (

  @SerialName("from"   ) var from   : String? = null,
  @SerialName("to"     ) var to     : String? = null,
  @SerialName("prop"   ) var prop   : NetworkTopProp?   = NetworkTopProp(),
  @SerialName("string" ) var string : String? = null

)