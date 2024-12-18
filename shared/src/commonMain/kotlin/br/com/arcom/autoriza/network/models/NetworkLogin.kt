package br.com.arcom.autoriza.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkLogin(
    @SerialName("idUsuario")
    val idUsuario: Long,
    @SerialName("senha")
    val senha: String
)