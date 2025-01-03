package br.com.arcom.apparcom.domain.interactor

import br.com.arcom.apparcom.domain.Interactor
import br.com.arcom.apparcom.domain.repository.UsuarioRepository
import br.com.arcom.apparcom.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class RealizarLogin(
    private val usuarioRepository: UsuarioRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<RealizarLogin.Params, Unit>() {

    data class Params(
        val idUsuario: Long,
        val senha: String
    )

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io){
            usuarioRepository.realizarLogin(
                idUsuario = params.idUsuario,
                senha = params.senha
            )
        }
    }
}