package br.com.arcom.sign.android.ui.home

import android.util.Log
import android.widget.Toast
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
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import coil.ImageLoader
import coil.compose.AsyncImage
import br.com.arcom.sign.android.core.domain.TopAnimePagingSource
import br.com.arcom.sign.model.anime.AnimeDetails
import br.com.arcom.sign.model.ranking.TypeRakingAnime
import br.com.arcom.sign.presentation.HomeUiState
import br.com.arcom.sign.presentation.HomeViewModel
import br.com.arcom.sign.util.onSuccess
import org.koin.compose.koinInject

@Composable
fun HomeRoute(viewModel: HomeViewModel = koinInject()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        refresh = viewModel::refresh
    )
}

@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    refresh: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = refresh ) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            }
        }
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
                    CardAnime(it)
                }
            }

            if (uiState.loadingTopAnimes) {
                item {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun CardAnime(anime: AnimeDetails) {
    val context = LocalContext.current

    Surface(
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(.9f)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
        ) {
            AsyncImage(model = anime.images.first().url,
                contentDescription = "Image anime",
                imageLoader = ImageLoader(context),
                modifier = Modifier.size(80.dp, 100.dp)
            )
            Text(
                text = "Anime: ${anime.title ?: "Não encontrado"}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}