package br.com.arcom.apparcom.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.arcom.apparcom.android.ui.app.AppArcomApp
import br.com.arcom.apparcom.android.ui.screens.login.LoginRoute
import br.com.arcom.apparcom.android.ui.designsystem.theme.AppArcomTheme
import br.com.arcom.apparcom.presentation.AppViewModel
import br.com.arcom.apparcom.presentation.AtualizacaoViewModel
import br.com.arcom.apparcom.util.ResultState
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: AppViewModel = koinInject()
            val atualizacao: AtualizacaoViewModel = koinInject()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val atualizacaoUiState by atualizacao.uiState.collectAsStateWithLifecycle()

            AppArcomTheme {
                uiState.logado?.let { logado ->
                    if (logado) {
                        AppArcomApp(
                            uiState = uiState,
                            atualizacao = atualizacaoUiState,
                            baixarAtualizacao = atualizacao::baixarAtualizacao,
                            clearUltimaVersao = viewModel::clearUltimaVersao
                        )
                    } else {
                        LoginRoute()
                    }
                }
            }
        }
    }
}