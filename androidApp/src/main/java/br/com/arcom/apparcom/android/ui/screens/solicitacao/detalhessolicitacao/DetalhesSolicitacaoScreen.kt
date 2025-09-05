package br.com.arcom.apparcom.android.ui.screens.solicitacao.detalhessolicitacao

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.arcom.apparcom.android.ui.designsystem.components.AppAnimeScaffold
import br.com.arcom.apparcom.android.ui.designsystem.components.AppAnimeTopBar
import br.com.arcom.apparcom.android.ui.designsystem.theme.Verde
import br.com.arcom.apparcom.android.ui.designsystem.theme.divider
import br.com.arcom.apparcom.model.solicitacao.SolicitacaoAceite
import br.com.arcom.apparcom.presentation.DetalhesSolicitacaoUiState
import br.com.arcom.apparcom.presentation.DetalhesSolicitacaoViewModel
import br.com.arcom.apparcom.util.format.formatBrasil
import br.com.arcom.apparcom.android.R
import br.com.arcom.apparcom.model.solicitacao.StatusSolicitacao
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
        clearMessage = viewModel::clearMessage,
        responderSolicitacao = viewModel::responderSolicitacao
    )
}

@Composable
fun DetalhesSolicitacaoScreen(
    uiState: DetalhesSolicitacaoUiState,
    onBackClick: () -> Unit,
    clearMessage: (Long) -> Unit,
    responderSolicitacao: (SolicitacaoAceite, Boolean) -> Unit,
) {

    AppAnimeScaffold(
        modifier = Modifier.fillMaxSize(),
        clearMessage = clearMessage,
        uiMessage = uiState.uiMessage,
        topBar = {
            AppAnimeTopBar(
                title = stringResource(R.string.solicitacao),
                onBackClick = onBackClick,
            )
        },
        loading = uiState.loadingRegistrando
    ) {
        LazyColumn(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            uiState.solicitacao?.let { solicitacaoDetalhes(it, responderSolicitacao) }
        }
    }
}


private fun LazyListScope.solicitacaoDetalhes(
    solicitacaoAceite: SolicitacaoAceite,
    responderSolicitacao: (SolicitacaoAceite, Boolean) -> Unit
) {
    item {
        Row(
            modifier = Modifier.fillMaxWidth(.9f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = solicitacaoAceite.tipoSolicitacao.descricao,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = solicitacaoAceite.data.formatBrasil(),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Normal
                )
            }
            if (solicitacaoAceite.status != StatusSolicitacao.AGUARDANDO_REPOSTA) {
                val color = when(solicitacaoAceite.status){
                    StatusSolicitacao.APROVADO -> Verde
                    StatusSolicitacao.NEGADO -> MaterialTheme.colorScheme.error
                    else -> MaterialTheme.colorScheme.onSurface
                }

                Text(
                    text = solicitacaoAceite.status.descricao,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .border(BorderStroke(2.dp, color), RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    color = color
                )
            }else{
                Column(
                    modifier = Modifier.width(IntrinsicSize.Max)
                ) {
                    Button(
                        onClick = { responderSolicitacao(solicitacaoAceite, true) },
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Verde,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.autorizar),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = { responderSolicitacao(solicitacaoAceite, false) },
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.onError
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.negar),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
    item {
        HorizontalDivider(
            color = MaterialTheme.colorScheme.onSurface.divider()
        )
    }
    item {
        Text(
            text = solicitacaoAceite.descricao,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth(.9f)
        )
    }
}
