package br.com.arcom.apparcom.android.ui.screens.home

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
import br.com.arcom.apparcom.presentation.HomeUiState
import br.com.arcom.apparcom.presentation.HomeViewModel
import br.com.arcom.apparcom.util.ResultState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeRoute(
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val permissions = rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)

    LaunchedEffect(Unit) {
        permissions.launchPermissionRequest()
    }

    HomeScreen(
        uiState = uiState,
        clearMessage = viewModel::clearMessage
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState = HomeUiState.Empty,
    clearMessage: (Long) -> Unit
) {
    val context = LocalContext.current
    AppAnimeScaffold(
        clearMessage = clearMessage,
        uiMessage = uiState.uiMessage
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues( bottom = 32.dp),
        ) {
            featuredContent(uiState.topAnimesUpcoming)
            listAnimes(uiState.topAnimesAiring, R.string.lancamentos)
            listAnimes(uiState.topAnimesRanking, R.string.melhores)
        }
    }
}

fun LazyListScope.featuredContent(
    animesState: ResultState<AnimeWithQuery?>
) {
    item {
        when (val state = animesState) {
            is ResultState.Success -> {
                // Prepara a lista de dados antes de passar para a UI
                val featuredAnimes = state.data?.animes.orEmpty().take(5)
                if (featuredAnimes.isNotEmpty()) {
                    FeaturedContentSuccess(animes = featuredAnimes)
                }
            }
            else -> {}
        }
        Spacer(Modifier.height(24.dp))
    }
}

fun LazyListScope.listAnimes(animes: ResultState<AnimeWithQuery?>, title: Int) {

    item {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingHorizontal),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

    item {
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingHorizontal,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            when (val state = animes) {
                is ResultState.Success -> {
                    val animeList = state.data?.animes.orEmpty()
                    if (animeList.isNotEmpty()) {
                        items(animeList) { anime ->
                            CardAnime(anime)
                        }
                    }
                }

                is ResultState.Loading -> {
                    items(10) {
                        CardAnimeLoading()
                    }
                }

                is ResultState.Failure -> {
                    // Exibir mensagem de erro
                }
            }
        }
    }

    item { Spacer(modifier = Modifier.height(16.dp)) }
}