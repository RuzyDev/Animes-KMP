package br.com.arcom.apparcom.android.ui.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import br.com.arcom.apparcom.android.ui.designsystem.theme.CornerSmallShapeAppAnime
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import org.koin.compose.koinInject
import br.com.arcom.apparcom.android.R
import br.com.arcom.apparcom.android.ui.designsystem.theme.lightColor
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent


@Composable
fun ImageWithState(
    modifier: Modifier = Modifier,
    model: Any?,
    contentDescription: String?,
    imageLoader: ImageLoader = koinInject(),
    contentScale: ContentScale = ContentScale.Crop
) {

    SubcomposeAsyncImage(
        model = model,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainer),
        imageLoader = imageLoader
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(0.3f),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            is AsyncImagePainter.State.Error -> {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(R.drawable.ic_broken_image),
                        contentDescription = "Erro ao carregar imagem",
                        tint = MaterialTheme.colorScheme.onSurface.lightColor(),
                        modifier = Modifier.fillMaxWidth(0.3f)
                    )
                }
            }

            else -> SubcomposeAsyncImageContent()
        }
    }
}
@Composable
fun FadingImageWithState(
    modifier: Modifier = Modifier,
    model: Any?,
    contentDescription: String?,
    imageLoader: ImageLoader = koinInject(),
    contentScale: ContentScale = ContentScale.Crop
) {

    val background = MaterialTheme.colorScheme.surface

    SubcomposeAsyncImage(
        model = model,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .drawWithContent {
                // Desenha o conteúdo da imagem original
                drawContent()

                // Desenha um gradiente retangular por cima da imagem
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            background,
                        ),
                        startY = size.height * 0.2f,
                        endY = size.height
                    )
                )
            },
        imageLoader = imageLoader
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(0.3f),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            is AsyncImagePainter.State.Error -> {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(R.drawable.ic_broken_image),
                        contentDescription = "Erro ao carregar imagem",
                        tint = MaterialTheme.colorScheme.onSurface.lightColor(),
                        modifier = Modifier.fillMaxWidth(0.3f)
                    )
                }
            }

            else -> SubcomposeAsyncImageContent()
        }
    }
}