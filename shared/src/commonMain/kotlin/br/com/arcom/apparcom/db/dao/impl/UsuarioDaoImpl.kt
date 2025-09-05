package br.com.arcom.apparcom.db.dao.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import br.com.arcom.apparcom.db.dao.UsuarioDao
import database.UsuarioEntity
import br.com.arcom.apparcom.network.models.NetworkUsuarioAppAnime
import database.UsuarioQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class UsuarioDaoImpl(
    private val usuarioQueries: UsuarioQueries
): UsuarioDao {
    override suspend fun insertOrUpdate(usuario: NetworkUsuarioAppAnime){
        usuarioQueries.deleteAll()
        usuarioQueries.insertOrUpdate(
            id = usuario.id,
            nome = usuario.nome,
            data_bloqueio = usuario.dataBloqueio?.toString()
        )
    }

    override fun getStream() = usuarioQueries.get().asFlow().mapToOneOrNull(Dispatchers.IO)
    override fun get(): UsuarioEntity? = usuarioQueries.get().executeAsOneOrNull()
}