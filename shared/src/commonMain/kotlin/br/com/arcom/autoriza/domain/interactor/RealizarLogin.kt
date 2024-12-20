package br.com.arcom.autoriza.domain.interactor

import br.com.arcom.autoriza.domain.Interactor
import br.com.arcom.autoriza.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.autoriza.domain.repository.UsuarioRepository
import br.com.arcom.autoriza.util.AppCoroutineDispatchers
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