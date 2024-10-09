package br.com.arcom.autoriza.network.models.top

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class From (

  @SerialName("day"   ) var day   : Int? = null,
  @SerialName("month" ) var month : Int? = null,
  @SerialName("year"  ) var year  : Int? = null

)