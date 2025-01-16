package br.com.arcom.apparcom.model.solicitacao

import br.com.arcom.apparcom.network.models.NetworkSolicitacaoAceite
import database.SolicitacaoAceiteEntity
import br.com.arcom.apparcom.db.util.toListLongDb
import kotlinx.datetime.LocalDateTime

data class SolicitacaoAceite(
    val id: String,
    val idSolicitacao: Long,
    val idUsuarios: List<Long>,
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
        idUsuarios = id_usuarios.toListLongDb(),
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
        idUsuarios = idUsuarios,
        idEmpresa = idEmpresa,
        descricao = descricao,
        status = status.value,
        tipoSolicitacao = tipoSolicitacao.value,
        data = data,
        dataResposta = dataResposta,
    )