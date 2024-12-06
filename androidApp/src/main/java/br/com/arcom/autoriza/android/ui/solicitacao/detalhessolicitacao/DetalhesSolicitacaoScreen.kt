package br.com.arcom.autoriza.android.ui.solicitacao.detalhessolicitacao

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.arcom.autoriza.android.R
import br.com.arcom.autoriza.android.ui.designsystem.components.AnimationError
import br.com.arcom.autoriza.android.ui.designsystem.components.AutorizaScaffold
import br.com.arcom.autoriza.android.ui.designsystem.components.AutorizaTopBar
import br.com.arcom.autoriza.presentation.DetalhesSolicitacaoUiState
import br.com.arcom.autoriza.presentation.DetalhesSolicitacaoViewModel
import br.com.arcom.autoriza.util.ResultState
import br.com.arcom.autoriza.util.onSuccess
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetalhesSolicitacaoRoute(
    id: String,
    onBackClick: () -> Unit,
    viewModel: DetalhesSolicitacaoViewModel = koinViewModel(parameters = { parametersOf(id) })
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DetalhesSolicitacaoScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        clearMessage = viewModel::clearMessage
    )
}

@Composable
fun DetalhesSolicitacaoScreen(
    uiState: DetalhesSolicitacaoUiState,
    onBackClick: () -> Unit,
    clearMessage: (Long) -> Unit,
) {

    AutorizaScaffold(
        modifier = Modifier.fillMaxSize(),
        clearMessage = clearMessage,
        uiMessage = uiState.uiMessage,
        topBar = {
            AutorizaTopBar(
                title = stringResource(R.string.solicitacao),
                onBackClick = onBackClick,
            )
        }
    ) {
        LazyColumn(Modifier.fillMaxWidth()) {
            when(uiState.solicitacao){
                is ResultState.Success -> {

                }
                is ResultState.Failure -> {
                    item {
                        AnimationError(
                            text = stringResource(R.string.ocorreu_erro_solicitacao)
                        )
                    }
                }
                is ResultState.Loading -> {

                }
            }
        }
    }
}

