package br.com.arcom.autoriza.model.solicitacao

import br.com.arcom.autoriza.db.solicitacao.SolicitacaoAceiteEntity
import kotlinx.datetime.LocalDateTime

data class SolicitacaoAceite(
    val idSolicitacao: Long,
    val idUsuario: Long,
    val idEmpresa: Long,
    val descricao: String,
    val status: StatusSolicitacao,
    val tipoSolicitacao: TipoSolicitacao,
    val dataSolicitacao: LocalDateTime
)

fun SolicitacaoAceiteEntity.toExternalModel() =
    SolicitacaoAceite(
        idSolicitacao = id_solicitacao,
        idUsuario = id_usuario,
        idEmpresa = id_empresa,
        descricao = descricao,
        status = StatusSolicitacao.getByValue(status),
        tipoSolicitacao = TipoSolicitacao.getByValue(tipo_solicitacao),
        dataSolicitacao = LocalDateTime.parse(data_solicitacao)
)