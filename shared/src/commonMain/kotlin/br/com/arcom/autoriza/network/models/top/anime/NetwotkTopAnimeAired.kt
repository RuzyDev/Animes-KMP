package br.com.arcom.autoriza.network.models.top.anime

import br.com.arcom.autoriza.network.models.top.NetworkTopProp
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NetwotkTopAnimeAired (
  @SerialName("from"   ) var from   : String? = null,
  @SerialName("to"     ) var to     : String? = null,
  @SerialName("prop"   ) var prop   : NetworkTopProp?   = NetworkTopProp(),
  @SerialName("string" ) var string : String? = null
)