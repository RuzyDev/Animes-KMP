package br.com.arcom.apparcom.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPushToken (
    @SerialName("token") val token: String,
    @SerialName("app") val app: String,
    @SerialName("setor") val setor: Long?
)