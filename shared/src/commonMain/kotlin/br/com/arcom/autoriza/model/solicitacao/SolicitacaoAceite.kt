package br.com.arcom.autoriza.model.solicitacao

import br.com.arcom.autoriza.db.solicitacao.SolicitacaoAceiteEntity

data class SolicitacaoAceite(
    val idSolicitacao: Long,
    val idUsuario: Long,
    val idEmpresa: Long,
    val descricao: String,
    val status: StatusSolicitacao,
    val tipoSolicitacao: TipoSolicitacao
)

//fun SolicitacaoAceiteEntity.toExternalModel() =
//    SolicitacaoAceite(
//        idSolicitacao = id_solicitacao,
//        idUsuario = idUsuario,
//        idEmpresa = idEmpresa,
//        descricao = descricao,
//        status = status,
//        tipoSolicitacao = tipoSolicitacao,
//)