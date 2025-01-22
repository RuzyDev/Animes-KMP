package br.com.arcom.apparcom.presentation

import br.com.arcom.apparcom.data.AndroidAppArcomFiles
import br.com.arcom.apparcom.data.datastore.AppArcomStorage
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

    private val appArcomStorage: AppArcomStorage by inject()
    private val observeUsuario: ObserveUsuario by inject()
    private val realizarAtualizacao: RealizarAtualizacao by inject()
    private val atualizarVersaoApp: AtualizarVersaoApp by inject()
    private val androidAppArcomFiles: AndroidAppArcomFiles by inject()

    private val _logado = appArcomStorage.getBooleanStream(Keys.LOGADO)
    private val _atualizacao = appArcomStorage.getStringStream(Keys.VERSAO_APP).map {
        if (it.isEmpty()) {
            false to it
            AtualizacaoApp(false, it, false)
        } else {
            val versaoInstalada = androidAppArcomFiles.getFile("apparcom-$it.apk") != null
            val versaoAtual = VersaoApp.parseVersao(ConstantsShared.VERSAO_APP)
            val ultimaVersao = VersaoApp.parseVersao(it)
            val possui = if (versaoAtual != null && ultimaVersao != null) {
                versaoAtual > ultimaVersao
            } else {
                false
            }
            AtualizacaoApp(possui, it, versaoInstalada)
        }
    }

    val uiState = combine(_logado, observeUsuario.flow,_atualizacao, realizarAtualizacao._progressFlow, ::AppUiState).stateIn(
        coroutineScope,
        SharingStarted.WhileSubscribed(5000),
        AppUiState.Empty
    )

    init {
        observeUsuario(Unit)
        observeUsuario.flow.onEach {
            it?.id?.let { it1 -> atualizarVersaoApp(idUsuario = it1) }
        }.launchIn(coroutineScope)
    }

    fun baixarAtualizacao(
        idUsuario: Long,
        ultimaVersao: String,
        baixarNovamente: Boolean = false,
        abrirApk: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        coroutineScope.launch {
            try {
                val fileName = realizarAtualizacao.baixar(idUsuario, ultimaVersao, baixarNovamente)
                abrirApk(fileName)
            } catch (e: Exception) {
                onError(e.message ?: "Ocorreu um erro")
                e.printStackTrace()
            }
        }
    }

    fun clearUltimaVersao() {
        coroutineScope.launch {
            appArcomStorage.clearString(Keys.VERSAO_APP)
        }
    }

    fun atualizarVersaoApp(idUsuario: Long) {
        coroutineScope.launch {
            atualizarVersaoApp.invoke(AtualizarVersaoApp.Params(idUsuario))
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
    val usuario: Usuario? = null,
    val atualizacao: AtualizacaoApp = AtualizacaoApp.Empty,
    val progress: Pair<Long, Long>? = null
) {
    companion object {
        val Empty = AppUiState()
    }
}


data class AtualizacaoApp(
    val possuiAtualizacao: Boolean,
    val ultimaVersao: String,
    val versaoInstalada: Boolean
){
    companion object {
        val Empty = AtualizacaoApp(false, "", false)
    }
}
