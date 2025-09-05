package br.com.arcom.apparcom.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.arcom.apparcom.android.ui.app.AppAnimeApp
import br.com.arcom.apparcom.android.ui.screens.login.LoginRoute
import br.com.arcom.apparcom.android.ui.designsystem.theme.AppAnimeTheme
import br.com.arcom.apparcom.presentation.AppViewModel
import br.com.arcom.apparcom.presentation.AtualizacaoViewModel
import br.com.arcom.apparcom.util.ResultState
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel: AppViewModel = koinInject()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            AppAnimeTheme {
               AppAnimeApp()
            }
        }
    }
}