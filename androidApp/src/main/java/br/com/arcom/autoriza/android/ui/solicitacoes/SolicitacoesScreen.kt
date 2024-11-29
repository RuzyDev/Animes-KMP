package br.com.arcom.autoriza.android.ui.solicitacoes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.arcom.autoriza.presentation.SolicitacoesUiState
import coil.ImageLoader
import coil.compose.AsyncImage
import br.com.arcom.autoriza.presentation.SolicitacoesViewModel
import br.com.arcom.autoriza.util.onSuccess
import org.koin.compose.koinInject

@Composable
fun SolicitacoesRoute(viewModel: SolicitacoesViewModel = koinInject()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SolicitacoesScreen(
        uiState = uiState,
        refresh = viewModel::refresh
    )
}

@Composable
private fun SolicitacoesScreen(
    uiState: SolicitacoesUiState,
    refresh: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            uiState.topAnimes.onSuccess {
                items(it) {

                }
            }
        }
    }
}