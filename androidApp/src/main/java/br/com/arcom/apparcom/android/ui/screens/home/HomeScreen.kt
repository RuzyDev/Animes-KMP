package br.com.arcom.apparcom.android.ui.screens.home

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.arcom.apparcom.android.ui.designsystem.components.AppArcomScaffold
import br.com.arcom.apparcom.android.ui.designsystem.theme.CornerShapeAppArcom
import br.com.arcom.apparcom.android.ui.designsystem.theme.TopCornerShapeAppArcomCard
import br.com.arcom.apparcom.designsystem.theme.secondaryColor
import br.com.arcom.apparcom.model.Usuario
import br.com.arcom.apparcom.presentation.HomeUiState
import br.com.arcom.apparcom.presentation.HomeViewModel
import br.com.arcom.apparcom.util.format.getPeriodoDia
import br.com.arcom.apparcom.android.R
import br.com.arcom.apparcom.android.core.domain.FirebaseUtil
import br.com.arcom.apparcom.util.format.getQtdPalavras
import br.com.arcom.apparcom.util.format.toNome
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.compose.koinInject

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeRoute(
    navigateToSolicitacoes: () -> Unit,
    viewModel: HomeViewModel = koinInject(),
    firebase: FirebaseUtil = koinInject()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val permissions = rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)

    LaunchedEffect(Unit) {
        viewModel.registrarPushToken {
            firebase.getToken()
        }
        permissions.launchPermissionRequest()
    }

    HomeScreen(
        navigateToSolicitacoes = navigateToSolicitacoes,
        uiState = uiState,
        clearMessage = viewModel::clearMessage
    )
}

@Composable
fun HomeScreen(
    navigateToSolicitacoes: () -> Unit,
    uiState: HomeUiState = HomeUiState.Empty,
    clearMessage: (Long) -> Unit
) {
    AppArcomScaffold(
        clearMessage = clearMessage,
        topBar = { TopBarHome(uiState.usuario) },
        uiMessage = uiState.uiMessage
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
        ) {
            menuSolicitacoes(
                navigateToSolicitacoes = navigateToSolicitacoes,
                qtdSolicitacoesPendentes = uiState.qtdSolicitacoesPendentes)
        }
    }
}

/**
 * Top Bar mostrado apenas na HomeScreen
 */
@Composable
private fun TopBarHome(usuario: Usuario?) {

    Box(
        Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = R.string.ola, getPeriodoDia()),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = usuario?.nome?.getQtdPalavras()?.toNome() ?: "Usuário",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.End,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "DrawableResourceTypeIcon",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .size(28.dp)
            )
        }
    }
}


private fun LazyListScope.menuSolicitacoes(
    navigateToSolicitacoes: () -> Unit,
    qtdSolicitacoesPendentes: Long
) {
    item {
        Row(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .height(IntrinsicSize.Min)
                .clip(CornerShapeAppArcom)
                .clickable(onClick = { navigateToSolicitacoes() })
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_check_illustration),
                contentDescription = "",
                modifier = Modifier
                    .height(98.dp)
            )
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                Text(
                    text = stringResource(R.string.solicitacao_descricao),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 1
                )
                Text(
                    text = pluralStringResource(R.plurals.pendentes_plural, qtdSolicitacoesPendentes.toInt(), qtdSolicitacoesPendentes),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 1
                )
            }
        }
    }
}

