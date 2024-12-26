package br.com.arcom.autoriza.domain.observers

import br.com.arcom.autoriza.domain.SubjectInteractor
import br.com.arcom.autoriza.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.autoriza.domain.repository.UsuarioRepository
import br.com.arcom.autoriza.model.Usuario
import br.com.arcom.autoriza.model.solicitacao.SolicitacaoAceite
import kotlinx.coroutines.flow.Flow

class ObserveUsuario(
    private val usuarioRepository: UsuarioRepository
) : SubjectInteractor<Unit, Usuario?>() {
    override fun createObservable(params: Unit): Flow<Usuario?> {
        return usuarioRepository.getUsuario()
    }
}
