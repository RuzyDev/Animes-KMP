package br.com.arcom.autoriza.network.models.top.anime

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class NetworkTopAnimeBroadcast (

  @SerialName("day"      ) var day      : String? = null,
  @SerialName("time"     ) var time     : String? = null,
  @SerialName("timezone" ) var timezone : String? = null,
  @SerialName("string"   ) var string   : String? = null

)