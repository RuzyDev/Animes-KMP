package br.com.arcom.sign.network.models.recomendation

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class UserRecomendation (

  @SerialName("url"      ) var url      : String? = null,
  @SerialName("username" ) var username : String? = null

)