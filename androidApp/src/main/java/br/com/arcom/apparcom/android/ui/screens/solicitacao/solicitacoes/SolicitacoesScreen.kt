package br.com.arcom.apparcom.android.ui.screens.solicitacao.solicitacoes

import Composable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.arcom.apparcom.android.ui.designsystem.components.AppArcomScaffold
import br.com.arcom.apparcom.android.ui.designsystem.components.AppArcomTopBar
import br.com.arcom.apparcom.android.ui.designsystem.components.NaoEncontrado
import br.com.arcom.apparcom.android.ui.designsystem.theme.CornerShapeAppArcomCard
import br.com.arcom.apparcom.designsystem.theme.Verde
import br.com.arcom.apparcom.model.solicitacao.SolicitacaoAceite
import br.com.arcom.apparcom.presentation.SolicitacoesUiState
import br.com.arcom.apparcom.presentation.SolicitacoesViewModel
import br.com.arcom.apparcom.android.R
import br.com.arcom.apparcom.android.ui.designsystem.components.DialogCheck
import br.com.arcom.apparcom.model.solicitacao.StatusSolicitacao
import br.com.arcom.apparcom.model.solicitacao.TipoSolicitacao
import br.com.arcom.apparcom.ui.designsystem.components.text.AppArcomTextFieldPesquisa
import org.koin.compose.koinInject

@Composable
fun SolicitacoesRoute(
    onBackClick: () -> Unit,
    navigateToDetalhesSolicitacao: (String) -> Unit,
    viewModel: SolicitacoesViewModel = koinInject()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SolicitacoesScreen(
        onBackClick = onBackClick,
        uiState = uiState,
        refresh = viewModel::refresh,
        responderSolicitacao = viewModel::responderSolicitacao,
        setSearch = viewModel::setSearch,
        setFiltro = viewModel::setFiltro,
        clearMessage = viewModel::clearMessage,
        navigateToDetalhesSolicitacao = navigateToDetalhesSolicitacao
    )
}

@Composable
private fun SolicitacoesScreen(
    onBackClick: () -> Unit,
    uiState: SolicitacoesUiState,
    refresh: () -> Unit,
    setSearch: (String) -> Unit,
    setFiltro: (TipoSolicitacao) -> Unit,
    responderSolicitacao: (SolicitacaoAceite, Boolean) -> Unit,
    clearMessage: (Long) -> Unit,
    navigateToDetalhesSolicitacao: (String) -> Unit
) {
    var pesquisa by remember { mutableStateOf("") }
    var filtro by remember { mutableStateOf(TipoSolicitacao.TODOS) }
    var openFiltro by remember { mutableStateOf(false) }

    if (openFiltro) {
        DialogCheck(
            onDismissRequest = { openFiltro = false },
            itens = TipoSolicitacao.entries,
            selected = filtro,
            label = { it.descricao },
            onSelected = {
                filtro = it
                openFiltro = false
                setFiltro(it)
            }
        )
    }

    AppArcomScaffold(
        modifier = Modifier.fillMaxSize(),
        clearMessage = clearMessage,
        uiMessage = uiState.uiMessage,
        topBar = {
            AppArcomTopBar(
                title = stringResource(R.string.solicitacoes),
                onBackClick = onBackClick,
                onRefresh = refresh
            )
        },
        loading = uiState.loadingSolicitacoes || uiState.loadingRegistrando
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                AppArcomTextFieldPesquisa(
                    modifier = Modifier.weight(1f),
                    text = pesquisa,
                    setText = {
                        pesquisa = it
                        setSearch(it)
                    }
                )
                AppArcomIcons.FILTRO.Composable(
                    modifier = Modifier
                        .size(32.dp)
                        .clickable(
                            interactionSource = null,
                            indication = null
                        ) {
                            openFiltro = !openFiltro
                        },
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            if (uiState.solicitacoes.isEmpty()) {
                NaoEncontrado(stringResource(R.string.sem_solicitacoes_no_momento))
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(uiState.solicitacoes) { solicitacao ->
                        CardSolicitacao(solicitacao, navigateToDetalhesSolicitacao) { resposta ->
                            responderSolicitacao(solicitacao, resposta)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardSolicitacao(
    solicitacao: SolicitacaoAceite,
    verDetalhes: (String) -> Unit,
    responder: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .clip(CornerShapeAppArcomCard)
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

        if (solicitacao.status != StatusSolicitacao.AGUARDANDO_REPOSTA) {
            val color = when(solicitacao.status){
                StatusSolicitacao.APROVADO -> Verde
                StatusSolicitacao.NEGADO -> MaterialTheme.colorScheme.error
                else -> MaterialTheme.colorScheme.onSurface
            }

            Text(
                text = solicitacao.status.descricao,
                style = MaterialTheme.typography.bodySmall,
                color = color,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(BorderStroke(2.dp, color), RoundedCornerShape(8.dp))
                    .padding(6.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }else{
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
}