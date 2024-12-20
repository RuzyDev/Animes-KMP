package br.com.arcom.autoriza.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(
    @SerialName("acessToken")
    val acessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String
)