package br.com.arcom.autoriza.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.arcom.autoriza.android.ui.app.AppArcomApp
import br.com.arcom.autoriza.android.ui.login.LoginRoute
import br.com.arcom.autoriza.android.ui.designsystem.theme.AppArcomTheme
import br.com.arcom.autoriza.presentation.AppViewModel
import br.com.arcom.autoriza.util.ResultState
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: AppViewModel = koinInject()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            AppArcomTheme {
                when(val data = uiState.logado){
                    is ResultState.Success -> {
                        if (data.data){
                            AppArcomApp(uiState.usuario)
                        }else{
                            LoginRoute()
                        }
                    }
                    is ResultState.Loading -> {

                    }
                    else -> {}
                }
            }
        }
    }
}