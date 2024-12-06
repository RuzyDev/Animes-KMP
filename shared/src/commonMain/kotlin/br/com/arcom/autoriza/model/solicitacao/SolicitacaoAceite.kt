package br.com.arcom.autoriza.model.solicitacao

import br.com.arcom.autoriza.db.solicitacao.SolicitacaoAceiteEntity
import br.com.arcom.autoriza.network.models.NetworkSolicitacaoAceite
import kotlinx.datetime.LocalDateTime

data class SolicitacaoAceite(
    val id: String,
    val idSolicitacao: Long,
    val idUsuario: Long,
    val idEmpresa: Long,
    val descricao: String,
    val status: StatusSolicitacao,
    val tipoSolicitacao: TipoSolicitacao,
    val data: LocalDateTime,
    val dataResposta: LocalDateTime?
)

fun SolicitacaoAceiteEntity.toExternalModel() =
    SolicitacaoAceite(
        id = id,
        idSolicitacao = id_solicitacao,
        idUsuario = id_usuario,
        idEmpresa = id_empresa,
        descricao = descricao,
        status = StatusSolicitacao.getByValue(status),
        tipoSolicitacao = TipoSolicitacao.getByValue(tipo_solicitacao),
        data = LocalDateTime.parse(data_),
        dataResposta = data_resposta?.let { LocalDateTime.parse(it) }
    )

fun SolicitacaoAceite.toNetwork() =
    NetworkSolicitacaoAceite(
        id = id,
        idSolicitacao = idSolicitacao,
        idUsuario = idUsuario,
        idEmpresa = idEmpresa,
        descricao = descricao,
        status = status.value,
        tipoSolicitacao = tipoSolicitacao.value,
        data = data,
        dataResposta = dataResposta,
    )