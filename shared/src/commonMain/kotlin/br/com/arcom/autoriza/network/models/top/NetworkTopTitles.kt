package br.com.arcom.autoriza.network.models.top

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class NetworkTopTitles (
  @SerialName("type"  ) var type  : String? = null,
  @SerialName("title" ) var title : String? = null
)