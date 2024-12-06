package br.com.arcom.autoriza.android.ui.perfil

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.arcom.autoriza.android.R
import br.com.arcom.autoriza.android.ui.designsystem.components.AutorizaScaffold
import br.com.arcom.autoriza.android.ui.designsystem.components.AutorizaTopBar
import br.com.arcom.autoriza.presentation.PerfilViewModel
import br.com.arcom.autoriza.presentation.util.UiMessage
import org.koin.compose.koinInject

@Composable
fun PerfilRoute(
    viewModel: PerfilViewModel = koinInject()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PerfilScreen(
        uiMessage = uiState,
        clearMessage = viewModel::clearMessage
    )
}

@Composable
private fun PerfilScreen(
    uiMessage: UiMessage?,
    clearMessage: (Long) -> Unit
) {
    AutorizaScaffold(
        topBar = {
            AutorizaTopBar(title = stringResource(R.string.perfil))
        },
        clearMessage = clearMessage,
        uiMessage = uiMessage
    ) {

    }
}

