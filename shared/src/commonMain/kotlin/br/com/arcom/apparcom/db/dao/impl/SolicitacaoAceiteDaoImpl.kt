package br.com.arcom.apparcom.db.dao.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import br.com.arcom.apparcom.db.dao.SolicitacaoAceiteDao
import br.com.arcom.apparcom.network.models.NetworkSolicitacaoAceite
import br.com.arcom.apparcom.db.solicitacao.SolicitacaoAceiteQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class SolicitacaoAceiteDaoImpl(
    private val solicitacaoAceiteQueries: SolicitacaoAceiteQueries
): SolicitacaoAceiteDao {
    override suspend fun insertOrUpdate(solicitacao: NetworkSolicitacaoAceite){
        solicitacaoAceiteQueries.insertOrUpdate(
            id = solicitacao.id,
            id_solicitacao = solicitacao.idSolicitacao,
            id_usuario = solicitacao.idUsuario,
            id_empresa = solicitacao.idEmpresa,
            descricao = solicitacao.descricao,
            status = solicitacao.status,
            tipo_solicitacao = solicitacao.tipoSolicitacao,
            data_ = solicitacao.data.toString(),
            data_resposta = solicitacao.dataResposta?.toString(),
        )
    }

    override fun getAllStream() = solicitacaoAceiteQueries.getAll().asFlow().mapToList(Dispatchers.IO)

    override fun getById(id: String) = solicitacaoAceiteQueries.getById(id).asFlow().mapToOne(Dispatchers.IO)
}