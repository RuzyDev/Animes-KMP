package br.com.arcom.autoriza.network.models.top

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class NetworkTopItems (

  @SerialName("count"    ) var count   : Int? = null,
  @SerialName("total"    ) var total   : Int? = null,
  @SerialName("per_page" ) var perPage : Int? = null

)