package br.com.arcom.apparcom.domain.observers

import br.com.arcom.apparcom.domain.SubjectInteractor
import br.com.arcom.apparcom.domain.repository.UsuarioRepository
import br.com.arcom.apparcom.model.Usuario
import kotlinx.coroutines.flow.Flow

class ObserveUsuario(
    private val usuarioRepository: UsuarioRepository
) : SubjectInteractor<Unit, Usuario?>() {
    override fun createObservable(params: Unit): Flow<Usuario?> {
        return usuarioRepository.getUsuario()
    }
}
