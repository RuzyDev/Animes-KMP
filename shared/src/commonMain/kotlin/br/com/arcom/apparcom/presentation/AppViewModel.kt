package br.com.arcom.apparcom.presentation

import br.com.arcom.apparcom.data.datastore.AppArcomStorage
import br.com.arcom.apparcom.data.datastore.Keys
import br.com.arcom.apparcom.domain.observers.ObserveUsuario
import br.com.arcom.apparcom.model.Usuario
import br.com.arcom.apparcom.util.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppViewModel : CoroutineViewModel(), KoinComponent {

    private val appArcomStorage: AppArcomStorage by inject()
    private val observeUsuario: ObserveUsuario by inject()

    private val logadoResult = MutableStateFlow<ResultState<Boolean>>(ResultState.Loading)
    private val _logado = appArcomStorage.getBooleanStream(Keys.LOGADO)

    val uiState = combine(logadoResult, observeUsuario.flow, ::AppUiState).stateIn(
        coroutineScope,
        SharingStarted.WhileSubscribed(5000),
        AppUiState.Empty
    )

    init {
        _logado.onEach { value ->
            val result = logadoResult.value
            if(result is ResultState.Success) {
                if(result.data != value) {
                    logadoResult.emit(ResultState.Success(value))
                }
            }else{
                logadoResult.emit(ResultState.Success(value))
            }
        }.launchIn(coroutineScope)
        observeUsuario(Unit)
    }
}

data class AppUiState(
    val logado: ResultState<Boolean> = ResultState.Loading,
    val usuario: Usuario? = null
){
    companion object{
        val Empty = AppUiState()
    }
}
