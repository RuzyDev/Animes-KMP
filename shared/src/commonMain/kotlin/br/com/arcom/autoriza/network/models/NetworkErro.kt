package br.com.arcom.autoriza.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPlayError(
    @SerialName("erro")
    val erro: NetworkErro,
)

@Serializable
data class NetworkErro (
    @SerialName("message")
    val message: String,
    @SerialName("stack")
    val stack: String
)
