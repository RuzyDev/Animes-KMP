package br.com.arcom.apparcom.presentation

import br.com.arcom.apparcom.data.AndroidAppAnimeFiles
import br.com.arcom.apparcom.data.datastore.AppAnimeStorage
import br.com.arcom.apparcom.data.datastore.Keys
import br.com.arcom.apparcom.domain.interactor.AtualizarVersaoApp
import br.com.arcom.apparcom.domain.interactor.RealizarAtualizacao
import br.com.arcom.apparcom.domain.observers.ObserveUsuario
import br.com.arcom.apparcom.model.Usuario
import br.com.arcom.apparcom.util.ConstantsShared
import br.com.arcom.apparcom.util.VersaoApp
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppViewModel : CoroutineViewModel(), KoinComponent {

    private val appArcomStorage: AppAnimeStorage by inject()
    private val observeUsuario: ObserveUsuario by inject()

    private val _logado = appArcomStorage.getBooleanStream(Keys.LOGADO)

    val uiState = combine(_logado, observeUsuario.flow, ::AppUiState).stateIn(
        coroutineScope,
        SharingStarted.WhileSubscribed(5000),
        AppUiState.Empty
    )

    init {
        observeUsuario(Unit)
    }

    fun clearUltimaVersao() {
        coroutineScope.launch {
            appArcomStorage.clearString(Keys.VERSAO_APP)
        }
    }


    fun observeUiState(onChange: (AppUiState) -> Unit) {
        uiState.onEach {
            onChange(it)
        }.launchIn(coroutineScope)
    }
}

data class AppUiState(
    val logado: Boolean? = null,
    val usuario: Usuario? = null
) {
    companion object {
        val Empty = AppUiState()
    }
}
