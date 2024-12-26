package br.com.arcom.autoriza.db.dao.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import br.com.arcom.autoriza.db.dao.SolicitacaoAceiteDao
import br.com.arcom.autoriza.db.dao.UsuarioDao
import br.com.arcom.autoriza.db.solicitacao.SolicitacaoAceiteQueries
import br.com.arcom.autoriza.db.solicitacao.UsuarioQueries
import br.com.arcom.autoriza.network.models.NetworkSolicitacaoAceite
import br.com.arcom.autoriza.network.models.NetworkUsuarioAppArcom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class UsuarioDaoImpl(
    private val usuarioQueries: UsuarioQueries
): UsuarioDao {
    override suspend fun insertOrUpdate(usuario: NetworkUsuarioAppArcom){
        usuarioQueries.deleteAll()
        usuarioQueries.insertOrUpdate(
            id = usuario.id,
            nome = usuario.nome,
            data_bloqueio = usuario.dataBloqueio?.toString()
        )
    }

    override fun get() = usuarioQueries.get().asFlow().mapToOne(Dispatchers.IO)
}