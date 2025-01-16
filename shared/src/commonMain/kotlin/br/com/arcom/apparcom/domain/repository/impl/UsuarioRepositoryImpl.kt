package br.com.arcom.apparcom.domain.repository.impl

import br.com.arcom.apparcom.data.datastore.AppArcomStorage
import br.com.arcom.apparcom.data.datastore.Keys
import br.com.arcom.apparcom.data.preferences.KeysPreferences
import br.com.arcom.apparcom.data.preferences.PreferencesManager
import br.com.arcom.apparcom.db.dao.UsuarioDao
import br.com.arcom.apparcom.domain.repository.UsuarioRepository
import br.com.arcom.apparcom.model.Usuario
import br.com.arcom.apparcom.model.toExternalModel
import br.com.arcom.apparcom.network.models.NetworkPushToken
import br.com.arcom.apparcom.network.service.UsuarioService
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

    override fun getUsuarioStream(): Flow<Usuario?> {
        return usuarioDao.getStream().map { it?.toExternalModel() }
    }

    override fun getUsuario(): Usuario? {
        return usuarioDao.get()?.toExternalModel()
    }

    override suspend fun registrarPushToken(token: String) {
        val usuario = getUsuario()
        if (usuario != null){
            usuarioService.enviarToken(
                NetworkPushToken(
                    token = token,
                    app = "app-arcom",
                    setor = usuario.id
                )
            )
        }
    }
}
