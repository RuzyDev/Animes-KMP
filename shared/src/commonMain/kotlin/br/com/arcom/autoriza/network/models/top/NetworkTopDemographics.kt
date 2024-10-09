package br.com.arcom.autoriza.network.models.top

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class NetworkTopDemographics (

  @SerialName("mal_id" ) var malId : Int?    = null,
  @SerialName("type"   ) var type  : String? = null,
  @SerialName("name"   ) var name  : String? = null,
  @SerialName("url"    ) var url   : String? = null

)