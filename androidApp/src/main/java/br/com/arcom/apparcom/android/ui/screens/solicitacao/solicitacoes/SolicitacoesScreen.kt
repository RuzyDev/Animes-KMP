package br.com.arcom.apparcom.android.ui.screens.solicitacao.solicitacoes

import AppAnimeIcons
import Composable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import br.com.arcom.apparcom.android.ui.designsystem.components.AppAnimeScaffold
import br.com.arcom.apparcom.android.ui.designsystem.components.AppAnimeTopBar
import br.com.arcom.apparcom.android.ui.designsystem.components.NaoEncontrado
import br.com.arcom.apparcom.android.ui.designsystem.theme.CornerShapeAppAnimeCard
import br.com.arcom.apparcom.android.ui.designsystem.theme.Verde
import br.com.arcom.apparcom.model.solicitacao.SolicitacaoAceite
import br.com.arcom.apparcom.presentation.SolicitacoesUiState
import br.com.arcom.apparcom.presentation.SolicitacoesViewModel
import br.com.arcom.apparcom.android.R
import br.com.arcom.apparcom.android.ui.designsystem.components.DialogCheck
import br.com.arcom.apparcom.model.solicitacao.StatusSolicitacao
import br.com.arcom.apparcom.model.solicitacao.TipoSolicitacao
import br.com.arcom.apparcom.ui.designsystem.components.text.AppAnimeTextFieldPesquisa
import br.com.arcom.apparcom.util.format.formatBrasil
import org.koin.androidx.compose.koinViewModel

@Composable
fun SolicitacoesRoute(
    onBackClick: () -> Unit,
    navigateToDetalhesSolicitacao: (String) -> Unit,
    viewModel: SolicitacoesViewModel = koinViewModel<SolicitacoesViewModel>()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SolicitacoesScreen(
        onBackClick = onBackClick,
        uiState = uiState,
        setPage = viewModel::setPage,
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
    setPage: (page: Long) -> Unit,
    setSearch: (String) -> Unit,
    setFiltro: (TipoSolicitacao) -> Unit,
    responderSolicitacao: (SolicitacaoAceite, Boolean) -> Unit,
    clearMessage: (Long) -> Unit,
    navigateToDetalhesSolicitacao: (String) -> Unit
) {
    var pesquisa by remember { mutableStateOf("") }
    var filtro by remember { mutableStateOf(TipoSolicitacao.TODOS) }
    var openFiltro by remember { mutableStateOf(false) }
    val lazyState = rememberLazyListState()
    val scope = rememberCoroutineScope()

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

    AppAnimeScaffold(
        modifier = Modifier.fillMaxSize(),
        clearMessage = clearMessage,
        uiMessage = uiState.uiMessage,
        topBar = {
            AppAnimeTopBar(
                title = stringResource(R.string.solicitacoes),
                onBackClick = onBackClick,
                onRefresh = { setPage(1) }
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
                AppAnimeTextFieldPesquisa(
                    modifier = Modifier.weight(1f),
                    text = pesquisa,
                    setText = {
                        pesquisa = it
                        setSearch(it)
                    }
                )
                AppAnimeIcons.FILTRO.Composable(
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
                    contentPadding = PaddingValues(bottom = 16.dp),
                    state = lazyState
                ) {
                    items(uiState.solicitacoes) { solicitacao ->
                        CardSolicitacao(solicitacao, navigateToDetalhesSolicitacao) { resposta ->
                            responderSolicitacao(solicitacao, resposta)
                        }
                    }
                    item {
                        Pagination(
                            currentPage = uiState.paginacao.page,
                            totalPages = uiState.paginacao.totalPaginas,
                            onPageChange = {
                                setPage(it)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Pagination(
    currentPage: Long,
    totalPages: Long,
    onPageChange: (Long) -> Unit
) {
    val list = (1..totalPages).toList()
    val filtro = list.filter {
        val restante = list.size - it
        if (restante >= 5) {
            it >= currentPage
        }else{
            true
        }
    }.take(5)

    Row(
        modifier = Modifier.padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botão de página anterior
        val voltarHabilitado = currentPage > 1
        AppAnimeIcons.VOLTAR.Composable(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(36.dp)
                .background(MaterialTheme.colorScheme.primary.copy(if (voltarHabilitado) 1f else 0.3f))
                .clickable(enabled = voltarHabilitado) {
                    onPageChange(currentPage - 1L)
                }
                .padding(8.dp),
            tint = MaterialTheme.colorScheme.onPrimary
        )


        // Botões de números de página
        filtro.forEach { page ->
            val isSelected = page == currentPage

            Box(
                Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(36.dp)
                    .border(
                        BorderStroke(
                            2.dp,
                            MaterialTheme.colorScheme.primary.copy(if (isSelected) 1f else 0.3f)
                        ), RoundedCornerShape(8.dp)
                    )
                    .clickable {
                        onPageChange(page)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    page.toString(),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }

        // Botão de página seguinte
        val proximoHabilitado = currentPage < totalPages
        AppAnimeIcons.AVANCAR.Composable(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(36.dp)
                .background(MaterialTheme.colorScheme.primary.copy(if (proximoHabilitado) 1f else 0.3f))
                .clickable(enabled = proximoHabilitado) {
                    onPageChange(currentPage + 1L)
                }
                .padding(8.dp),
            tint = MaterialTheme.colorScheme.onPrimary
        )
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
            .clip(CornerShapeAppAnimeCard)
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
                solicitacao.data.formatBrasil(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier,
                fontWeight = FontWeight.SemiBold
            )

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
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (solicitacao.status != StatusSolicitacao.AGUARDANDO_REPOSTA) {
            val color = when (solicitacao.status) {
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
        } else {
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