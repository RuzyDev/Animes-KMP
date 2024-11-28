package br.com.arcom.autoriza.db.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import br.com.arcom.autoriza.db.solicitacao.SolicitacaoAceiteQueries
import br.com.arcom.autoriza.model.solicitacao.SolicitacaoAceite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class SolicitacaoAceiteDao(
    private val solicitacaoAceiteQueries: SolicitacaoAceiteQueries
) {
    suspend fun insertOrUpdate(solicitacao: SolicitacaoAceite){
        solicitacaoAceiteQueries.insertOrUpdate(
            id_solicitacao = solicitacao.idSolicitacao,
            id_usuario = solicitacao.idUsuario,
            id_empresa = solicitacao.idEmpresa,
            descricao = solicitacao.descricao,
            status = solicitacao.status.value,
            tipo_solicitacao = solicitacao.tipoSolicitacao.value
        )
    }

    fun getAllStream() = solicitacaoAceiteQueries.getAll().asFlow().mapToList(Dispatchers.IO)
}