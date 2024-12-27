package br.com.arcom.autoriza.presentation

import br.com.arcom.autoriza.domain.observers.ObserveUsuario
import br.com.arcom.autoriza.model.Usuario
import br.com.arcom.autoriza.presentation.util.UiMessage
import br.com.arcom.autoriza.presentation.util.UiMessageManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : CoroutineViewModel(), KoinComponent {

    private val observeUsuario: ObserveUsuario by inject()
    private val uiMessage = UiMessageManager()

    val uiState: StateFlow<HomeUiState> =
        combine(
            uiMessage.observable,
            observeUsuario.flow,
            ::HomeUiState
        ).stateIn(
            coroutineScope,
            SharingStarted.WhileSubscribed(5000),
            HomeUiState.Empty
        )

    fun clearMessage(id: Long) {
        coroutineScope.launch {
            uiMessage.clearMessage(id)
        }
    }

    init {
        observeUsuario(Unit)
    }
}

data class HomeUiState(
    val uiMessage: UiMessage? = null,
    val usuario: Usuario? = null
) {
    companion object {
        val Empty = HomeUiState()
    }
}