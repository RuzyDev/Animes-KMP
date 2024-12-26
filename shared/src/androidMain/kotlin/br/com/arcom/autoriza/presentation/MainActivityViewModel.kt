package br.com.arcom.autoriza.presentation

import androidx.lifecycle.ViewModel
import br.com.arcom.autoriza.data.datastore.AppArcomStorage
import br.com.arcom.autoriza.data.datastore.Keys
import br.com.arcom.autoriza.presentation.util.UiMessage
import br.com.arcom.autoriza.util.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivityViewModel : CoroutineViewModel(), KoinComponent {

    private val appArcomStorage: AppArcomStorage by inject()

    private val logadoResult = MutableStateFlow<ResultState<Boolean>>(ResultState.Loading)
    private val _logado = appArcomStorage.getBooleanStream(Keys.LOGADO)

    val states = logadoResult.asStateFlow()

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
    }
}
