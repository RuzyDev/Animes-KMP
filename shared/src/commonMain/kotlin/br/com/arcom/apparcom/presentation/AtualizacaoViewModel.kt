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

class AtualizacaoViewModel : CoroutineViewModel(), KoinComponent {

    private val appArcomStorage: AppAnimeStorage by inject()
    private val realizarAtualizacao: RealizarAtualizacao by inject()
    private val atualizarVersaoApp: AtualizarVersaoApp by inject()
    private val androidAppAnimeFiles: AndroidAppAnimeFiles by inject()
    private val _atualizacao = appArcomStorage.getStringStream(Keys.VERSAO_APP).map {
        if (it.isEmpty()) {
            false to it
            AtualizacaoApp(false, it, false)
        } else {
            val versaoInstalada = androidAppAnimeFiles.getFile("apparcom-$it.apk") != null
            val versaoAtual = VersaoApp.parseVersao(ConstantsShared.VERSAO_APP)
            val ultimaVersao = VersaoApp.parseVersao(it)
            val possui = if (versaoAtual != null && ultimaVersao != null) {
                versaoAtual < ultimaVersao
            } else {
                false
            }
            AtualizacaoApp(possui, it, versaoInstalada)
        }
    }

    val uiState = combine(realizarAtualizacao._progressFlow, _atualizacao, ::AtualizacaoUiState).stateIn(
        coroutineScope,
        SharingStarted.WhileSubscribed(5000),
        AtualizacaoUiState.Empty
    )


    fun baixarAtualizacao(
        idUsuario: Long,
        ultimaVersao: String,
        baixarNovamente: Boolean = false,
        abrirApk: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        coroutineScope.launch {
            try {
                val fileName = realizarAtualizacao.baixar( ultimaVersao, baixarNovamente)
                abrirApk(fileName)
            } catch (e: Exception) {
                onError(e.message ?: "Ocorreu um erro")
                e.printStackTrace()
            }
        }
    }

    fun atualizarVersaoApp(idUsuario: Long) {
        coroutineScope.launch {
            atualizarVersaoApp.invoke(AtualizarVersaoApp.Params(idUsuario))
        }
    }
}

data class AtualizacaoUiState(
    val progress: Pair<Long, Long>? = null,
    val atualizacao: AtualizacaoApp = AtualizacaoApp.Empty
){
    companion object {
        val Empty = AtualizacaoUiState()
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