package br.com.arcom.autoriza.network.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkUsuarioAppArcom(
    @SerialName("id")
    val id: Long,
    @SerialName("nome")
    val nome: String,
    @SerialName("dataBloqueio")
    val dataBloqueio: LocalDateTime?,
    @SerialName("token")
    val token: String,
)