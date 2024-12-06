package br.com.arcom.autoriza.network.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class NetworkSolicitacaoAceite (
  @SerialName("id")               val id: String,
  @SerialName("idSolicitacao")    val idSolicitacao: Long,
  @SerialName("idUsuario")        val idUsuario: Long,
  @SerialName("data")             val data: LocalDateTime,
  @SerialName("dataResposta")     val dataResposta: LocalDateTime?,
  @SerialName("descricao")        val descricao: String,
  @SerialName("tipoSolicitacao")  val tipoSolicitacao: String,
  @SerialName("idEmpresa")        val idEmpresa: Long,
  @SerialName("status")           val status: String
)