package br.com.arcom.apparcom.android.core.service.notificacao.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

enum class TipoNotificacao (val value: String){
    RETORNO_SOLICITACAO("retorno-solicitacao"),
    NOVA_SOLICITACAO("nova-solicitacao")
}