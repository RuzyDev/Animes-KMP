package br.com.arcom.apparcom.domain.interactor

import br.com.arcom.apparcom.domain.Interactor
import br.com.arcom.apparcom.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.apparcom.domain.repository.UsuarioRepository
import br.com.arcom.apparcom.model.Usuario
import br.com.arcom.apparcom.model.solicitacao.SolicitacaoAceite
import br.com.arcom.apparcom.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class GetUsuario(
    private val usuarioRepository: UsuarioRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<Unit, Usuario?>() {

    override suspend fun doWork(params: Unit): Usuario? {
        return withContext(dispatchers.io){
            usuarioRepository.getUsuario()
        }
    }
}