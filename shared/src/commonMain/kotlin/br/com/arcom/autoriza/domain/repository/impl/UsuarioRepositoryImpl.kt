package br.com.arcom.autoriza.domain.repository.impl

import br.com.arcom.autoriza.data.datastore.AppArcomStorage
import br.com.arcom.autoriza.data.datastore.Keys
import br.com.arcom.autoriza.data.preferences.KeysPreferences
import br.com.arcom.autoriza.data.preferences.PreferencesManager
import br.com.arcom.autoriza.db.dao.SolicitacaoAceiteDao
import br.com.arcom.autoriza.db.dao.UsuarioDao
import br.com.arcom.autoriza.domain.repository.UsuarioRepository
import br.com.arcom.autoriza.model.Usuario
import br.com.arcom.autoriza.model.toExternalModel
import br.com.arcom.autoriza.network.service.UsuarioService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UsuarioRepositoryImpl(
    private val usuarioService: UsuarioService,
    private val usuarioDao: UsuarioDao,
    private val appArcomStorage: AppArcomStorage,
    private val preferencesManager: PreferencesManager
) : UsuarioRepository {

    override suspend fun realizarLogin(idUsuario: Long, senha: String) {
        val usuario = usuarioService.login(idUsuario, senha)
        if (usuario != null){
            usuarioDao.insertOrUpdate(usuario)
            appArcomStorage.emitBoolean(true, Keys.LOGADO)
            preferencesManager.save(key = KeysPreferences.ACCESS_TOKEN, value = usuario.token)
            preferencesManager.save(key = KeysPreferences.REFRESH_TOKEN, value = usuario.token)
        }else{
            throw Exception("Usuário não encontrado!")
        }
    }

    override fun getUsuario(): Flow<Usuario?> {
        return usuarioDao.get().map { it?.toExternalModel() }
    }

}
