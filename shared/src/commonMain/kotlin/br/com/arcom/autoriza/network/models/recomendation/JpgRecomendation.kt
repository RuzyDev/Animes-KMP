package br.com.arcom.autoriza.network.models.recomendation

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class JpgRecomendation (

  @SerialName("image_url"       ) var imageUrl      : String? = null,
  @SerialName("small_image_url" ) var smallImageUrl : String? = null,
  @SerialName("large_image_url" ) var largeImageUrl : String? = null

)