package br.com.arcom.apparcom.domain.interactor

import br.com.arcom.apparcom.domain.Interactor
import br.com.arcom.apparcom.domain.repository.UsuarioRepository
import br.com.arcom.apparcom.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class AtualizarPushToken(
    private val dispatchers: AppCoroutineDispatchers,
    private val usuarioRepository: UsuarioRepository
) : Interactor<AtualizarPushToken.Params, Unit>() {

    data class Params(val token: String)

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io) {
            usuarioRepository.registrarPushToken(params.token)
        }
    }
}