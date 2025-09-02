package br.com.arcom.apparcom.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkAnimeWithPagination (
    @SerialName("pagination" ) val pagination : NetworkPagination?  = null,
    @SerialName("data"       ) val animes     : List<NetworkAnime>? = null
)