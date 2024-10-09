package br.com.arcom.autoriza.network.models.recomendation

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ImagesRecomendation (

  @SerialName("jpg"  ) var jpgRecomendation  : JpgRecomendation?  = JpgRecomendation()

)