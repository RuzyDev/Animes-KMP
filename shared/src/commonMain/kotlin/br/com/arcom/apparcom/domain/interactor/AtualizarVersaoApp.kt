package br.com.arcom.apparcom.domain.interactor

import br.com.arcom.apparcom.data.datastore.AppArcomStorage
import br.com.arcom.apparcom.data.datastore.Keys
import br.com.arcom.apparcom.domain.Interactor
import br.com.arcom.apparcom.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.apparcom.domain.repository.UsuarioRepository
import br.com.arcom.apparcom.model.Usuario
import br.com.arcom.apparcom.model.solicitacao.SolicitacaoAceite
import br.com.arcom.apparcom.network.service.AppService
import br.com.arcom.apparcom.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class AtualizarVersaoApp(
    private val appArcomStorage: AppArcomStorage,
    private val appService: AppService,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<AtualizarVersaoApp.Params, Unit>() {

    data class Params(val idUsuario: Long)

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io){
            val versaoApp = appService.getVersaoApp(params.idUsuario) ?: throw Exception("Versão não encontrada")
            appArcomStorage.emitString(versaoApp, Keys.VERSAO_APP)
        }
    }
}