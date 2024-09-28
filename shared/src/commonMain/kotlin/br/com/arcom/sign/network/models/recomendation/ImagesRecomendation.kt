package br.com.arcom.sign.network.models.recomendation

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ImagesRecomendation (

  @SerialName("jpg"  ) var jpgRecomendation  : JpgRecomendation?  = JpgRecomendation()

)