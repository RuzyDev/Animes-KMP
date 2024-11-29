package br.com.arcom.autoriza.db.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import br.com.arcom.autoriza.db.solicitacao.SolicitacaoAceiteQueries
import br.com.arcom.autoriza.network.models.NetworkSolicitacaoAceite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class SolicitacaoAceiteDao(
    private val solicitacaoAceiteQueries: SolicitacaoAceiteQueries
) {
    suspend fun insertOrUpdate(solicitacao: NetworkSolicitacaoAceite){
        solicitacaoAceiteQueries.insertOrUpdate(
            id_solicitacao = solicitacao.id,
            id_usuario = solicitacao.idUsuario,
            id_empresa = solicitacao.idEmpresa,
            descricao = solicitacao.descricao,
            status = solicitacao.status,
            tipo_solicitacao = solicitacao.tipoSolicitacao,
            data_solicitacao = solicitacao.data.toString()
        )
    }

    fun getAllStream() = solicitacaoAceiteQueries.getAll().asFlow().mapToList(Dispatchers.IO)
}