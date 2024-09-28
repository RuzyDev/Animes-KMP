package br.com.arcom.sign.network.models.top.manga

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class NetworkTopManga (
  @SerialName("pagination" ) var networkTopMangaPagination : NetworkTopMangaPagination?     = NetworkTopMangaPagination(),
  @SerialName("data"       ) var data       : ArrayList<NetworkTopMangaData> = arrayListOf()
)