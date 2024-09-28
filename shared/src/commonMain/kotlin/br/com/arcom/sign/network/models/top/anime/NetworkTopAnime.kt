package br.com.arcom.sign.network.models.top.anime

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class NetworkTopAnime(

    @SerialName("pagination") var networkTopAnimePagination: NetworkTopAnimePagination? = NetworkTopAnimePagination(),
    @SerialName("data") var data: List<NetworkTopAnimeData> = emptyList()

)