package br.com.arcom.apparcom.db.dao.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import br.com.arcom.apparcom.db.dao.SolicitacaoAceiteDao
import br.com.arcom.apparcom.db.solicitacao.SolicitacaoAceiteEntity
import br.com.arcom.apparcom.network.models.NetworkSolicitacaoAceite
import br.com.arcom.apparcom.db.solicitacao.SolicitacaoAceiteQueries
import br.com.arcom.apparcom.model.solicitacao.TipoSolicitacao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class SolicitacaoAceiteDaoImpl(
    private val solicitacaoAceiteQueries: SolicitacaoAceiteQueries
) : SolicitacaoAceiteDao {
    override suspend fun insertOrUpdate(solicitacao: NetworkSolicitacaoAceite) {
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

    override fun getAllStream(search: String, filtro: TipoSolicitacao): Flow<List<SolicitacaoAceiteEntity>> {
        val pesquisa = if (search.isEmpty()) "%" else "%$search%"
        return solicitacaoAceiteQueries.getAll(pesquisa, filtro.value).asFlow()
            .mapToList(Dispatchers.IO)
    }

    override fun getById(id: String) =
        solicitacaoAceiteQueries.getById(id).asFlow().mapToOne(Dispatchers.IO)

    override fun getQtdNaoRespondidas(): Flow<Long> =
        solicitacaoAceiteQueries.getQtdNaoRespondidas().asFlow().mapToOne(Dispatchers.IO)
}