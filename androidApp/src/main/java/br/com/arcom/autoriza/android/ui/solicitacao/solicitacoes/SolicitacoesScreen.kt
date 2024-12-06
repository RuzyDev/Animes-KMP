package br.com.arcom.autoriza.android.ui.solicitacao.solicitacoes

import AutorizaIcons
import Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.arcom.autoriza.android.R
import br.com.arcom.autoriza.android.ui.designsystem.components.AutorizaScaffold
import br.com.arcom.autoriza.android.ui.designsystem.components.AutorizaTopBar
import br.com.arcom.autoriza.android.ui.designsystem.theme.CornerShapeAutoriza
import br.com.arcom.autoriza.android.ui.designsystem.theme.CornerShapeAutorizaCard
import br.com.arcom.autoriza.designsystem.theme.Verde
import br.com.arcom.autoriza.model.solicitacao.SolicitacaoAceite
import br.com.arcom.autoriza.presentation.SolicitacoesUiState
import br.com.arcom.autoriza.presentation.SolicitacoesViewModel
import br.com.arcom.autoriza.util.format.formatBrasil
import br.com.arcom.autoriza.util.onSuccess
import org.koin.compose.koinInject

@Composable
fun SolicitacoesRoute(
    navigateToDetalhesSolicitacao: (String) -> Unit,
    viewModel: SolicitacoesViewModel = koinInject()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SolicitacoesScreen(
        uiState = uiState,
        refresh = viewModel::refresh,
        responderSolicitacao = viewModel::responderSolicitacao,
        clearMessage = viewModel::clearMessage,
        navigateToDetalhesSolicitacao = navigateToDetalhesSolicitacao
    )
}

@Composable
private fun SolicitacoesScreen(
    uiState: SolicitacoesUiState,
    refresh: () -> Unit,
    responderSolicitacao: (SolicitacaoAceite, Boolean) -> Unit,
    clearMessage: (Long) -> Unit,
    navigateToDetalhesSolicitacao: (String) -> Unit
) {
    AutorizaScaffold(
        modifier = Modifier.fillMaxSize(),
        clearMessage = clearMessage,
        uiMessage = uiState.uiMessage,
        topBar = {
            AutorizaTopBar(title = stringResource(R.string.solicitacoes))
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            uiState.solicitacoes.onSuccess {
                items(it) { solicitacao ->
                    CardSolicitacao(solicitacao, navigateToDetalhesSolicitacao) { resposta ->
                        responderSolicitacao(solicitacao, resposta)
                    }
                }
            }
        }
    }
}

@Composable
fun CardSolicitacao(solicitacao: SolicitacaoAceite, verDetalhes: (String) -> Unit, responder: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .clip(CornerShapeAutorizaCard)
            .clickable { verDetalhes(solicitacao.id) }
            .fillMaxWidth(.9f)
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
        ) {
            Text(
                solicitacao.tipoSolicitacao.descricao,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                solicitacao.descricao,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Text(
            text = stringResource(R.string.autorizar),
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable { responder(true) }
                .background(Verde)
                .padding(6.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(R.string.negar),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onError,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable { responder(false) }
                .background(MaterialTheme.colorScheme.error)
                .padding(6.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}