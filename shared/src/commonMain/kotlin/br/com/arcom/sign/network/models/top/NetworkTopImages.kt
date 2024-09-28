package br.com.arcom.sign.network.models.top

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class NetworkTopImages (
  @SerialName("jpg"  ) var jpg  : NetworkTopJpg?  = NetworkTopJpg()
)


@Serializable
data class NetworkTopJpg (
  @SerialName("image_url"       ) var imageUrl      : String? = null,
  @SerialName("small_image_url" ) var smallImageUrl : String? = null,
  @SerialName("large_image_url" ) var largeImageUrl : String? = null
)