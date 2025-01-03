package br.com.arcom.apparcom.db.dao.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import br.com.arcom.apparcom.db.dao.UsuarioDao
import br.com.arcom.apparcom.network.models.NetworkUsuarioAppArcom
import br.com.arcom.apparcom.db.solicitacao.UsuarioQueries
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

    override fun get() = usuarioQueries.get().asFlow().mapToOneOrNull(Dispatchers.IO)
}