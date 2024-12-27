package br.com.arcom.autoriza.android.ui.screens.home

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.arcom.autoriza.android.R
import br.com.arcom.autoriza.android.ui.designsystem.components.AppArcomScaffold
import br.com.arcom.autoriza.android.ui.designsystem.theme.CornerShapeAppArcom
import br.com.arcom.autoriza.android.ui.designsystem.theme.TopCornerShapeAppArcomCard
import br.com.arcom.autoriza.designsystem.theme.secondaryColor
import br.com.arcom.autoriza.model.Usuario
import br.com.arcom.autoriza.presentation.HomeUiState
import br.com.arcom.autoriza.presentation.HomeViewModel
import br.com.arcom.autoriza.util.format.getPeriodoDia
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.compose.koinInject

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeRoute(
    navigateToSolicitacoes: () -> Unit,
    viewModel: HomeViewModel = koinInject()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val permissions = rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)

    LaunchedEffect(Unit) {
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
        containerColor = MaterialTheme.colorScheme.primary,
        uiMessage = uiState.uiMessage
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .clip(TopCornerShapeAppArcomCard)
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
        ) {
            menuSolicitacoes(navigateToSolicitacoes)
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
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "DrawableResourceTypeIcon",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .size(32.dp)
            )

            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                Text(
                    text = stringResource(id = R.string.ola, getPeriodoDia()),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimary.secondaryColor(),
                    textAlign = TextAlign.End
                )
                Text(
                    text = usuario?.nome ?: "Usuário",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.End,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


private fun LazyListScope.menuSolicitacoes(
    navigateToSolicitacoes: () -> Unit
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
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.Start) {
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
                    text = pluralStringResource(R.plurals.pendentes_plural, 10, 10),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 1
                )
            }
            Image(
                painter = painterResource(R.drawable.ic_check_illustration),
                contentDescription = "",
                modifier = Modifier
                    .height(86.dp)
            )
        }
    }
}

