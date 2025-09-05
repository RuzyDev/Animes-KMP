package br.com.arcom.apparcom.android.ui.designsystem.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.arcom.apparcom.android.R
import br.com.arcom.apparcom.android.ui.designsystem.theme.CornerSmallShapeAppAnime
import br.com.arcom.apparcom.model.Anime
import br.com.arcom.apparcom.util.format.toStringApp

@Composable
fun CardAnime(anime: Anime) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ImageWithState(
            model = anime.image,
            contentDescription = anime.title,
            modifier = Modifier
                .clip(CornerSmallShapeAppAnime)
                .size(150.dp, 200.dp)
        )
        Text(
            anime.title.toStringApp(),
            modifier = Modifier.width(125.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CardAnimeLoading() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CornerSmallShapeAppAnime)
                .size(125.dp, 175.dp)
                .loading()
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .size(125.dp, 12.dp)
                .loading()
        )
    }
}

@Composable
fun FeaturedContentSuccess(animes: List<Anime>) {
    val pagerState = rememberPagerState { animes.size }
    val currentAnime = animes[pagerState.currentPage]

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        // Pager com as imagens de fundo
        HorizontalPager(state = pagerState) { page ->
            val anime = animes[page]
            FadingImageWithState(
                modifier = Modifier.fillMaxSize(),
                model = anime.imageLarge,
                contentDescription = anime.title
            )
        }

        // Conteúdo sobreposto (título, indicador, etc.)
        FeaturedContentOverlay(
            pagerState = pagerState,
            anime = currentAnime
        )
    }
}

@Composable
private fun BoxScope.FeaturedContentOverlay(
    pagerState: PagerState,
    anime: Anime
) {
    Column(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 24.dp)
    ) {
        Text(
            text = stringResource(R.string.upcoming),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = anime.title.toStringApp(),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.ExtraBold
        )
        HorizontalPagerIndicator(
            pageCount = pagerState.pageCount,
            currentPage = pagerState.currentPage,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}