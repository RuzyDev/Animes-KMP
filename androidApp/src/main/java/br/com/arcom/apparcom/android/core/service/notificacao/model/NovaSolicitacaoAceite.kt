package br.com.arcom.apparcom.android.core.service.notificacao.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NovaSolicitacaoAceite (
    @SerialName("tipoSolicitacao")
    val tipoSolicitacao: String,
    @SerialName("msg")
    val msg: String,
)