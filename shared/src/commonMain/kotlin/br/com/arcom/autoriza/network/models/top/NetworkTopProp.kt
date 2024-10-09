package br.com.arcom.autoriza.network.models.top

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class NetworkTopProp (
    @SerialName("from" ) var from : From? = From(),
    @SerialName("to"   ) var to   : To?   = To()
)