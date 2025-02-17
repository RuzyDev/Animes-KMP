package br.com.arcom.apparcom.android.core.service.notificacao.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RespostaSolicitacaoAceite (
    @SerialName("id")
    val id: String,
    @SerialName("tipoSolicitacao")
    val tipoSolicitacao: String,
    @SerialName("status")
    val status: String,
    @SerialName("msg")
    val msg: String,
)