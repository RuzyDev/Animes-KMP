package br.com.arcom.sign.network.models.top

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class To (

  @SerialName("day"   ) var day   : String? = null,
  @SerialName("month" ) var month : String? = null,
  @SerialName("year"  ) var year  : String? = null

)