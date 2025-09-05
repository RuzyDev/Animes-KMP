package br.com.arcom.apparcom.android.ui.screens.animedetails

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.arcom.apparcom.android.R
import br.com.arcom.apparcom.android.ui.designsystem.components.AppAnimeScaffold
import br.com.arcom.apparcom.android.ui.designsystem.components.CardAnime
import br.com.arcom.apparcom.android.ui.designsystem.components.CardAnimeLoading
import br.com.arcom.apparcom.android.ui.designsystem.components.FeaturedContentSuccess
import br.com.arcom.apparcom.android.ui.designsystem.components.PaddingHorizontal
import br.com.arcom.apparcom.model.AnimeWithQuery
import br.com.arcom.apparcom.presentation.AnimeDetailsUiState
import br.com.arcom.apparcom.presentation.AnimeDetailsViewModel
import br.com.arcom.apparcom.util.ResultState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AnimeDetailsRoute(
    viewModel: AnimeDetailsViewModel = koinViewModel<AnimeDetailsViewModel>()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AnimeDetailsScreen(
        uiState = uiState,
        clearMessage = viewModel::clearMessage
    )
}

@Composable
fun AnimeDetailsScreen(
    uiState: AnimeDetailsUiState = AnimeDetailsUiState.Empty,
    clearMessage: (Long) -> Unit
) {
    val context = LocalContext.current
    AppAnimeScaffold(
        clearMessage = clearMessage,
        uiMessage = uiState.uiMessage
    ) {

    }
}