package br.com.arcom.apparcom.network.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class NetworkSolicitacaoAceitePaginado (
  @SerialName("solicitacoes")    val solicitacoes: List<NetworkSolicitacaoAceite>,
  @SerialName("totalPaginas")    val totalPaginas: Long
)