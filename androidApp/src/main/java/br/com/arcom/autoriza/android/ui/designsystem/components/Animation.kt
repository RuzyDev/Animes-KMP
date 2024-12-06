package br.com.arcom.autoriza.android.ui.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.arcom.autoriza.android.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LoadingAnimacao(
    modifier: Modifier = Modifier,
    size: Dp = 56.dp
) {
    Box(modifier = modifier.requiredSize(size)) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo app",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .requiredSize(size / 2)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun InfiniteAnimation(
    modifier: Modifier = Modifier,
    rawDrawable: Int,
    startAnimation: Boolean,
    speed: Float = 1f,
    contentCrop: ContentScale = ContentScale.Crop
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(rawDrawable)
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = startAnimation,
        speed = speed,
        restartOnPlay = false
    )

    Box(modifier = modifier) {
        LottieAnimation(
            composition,
            { progress },
            contentScale = contentCrop,
        )
    }
}

@Composable
fun EnvioAnimacaoSucess(
    startAnimacao: Boolean,
    funcaoSucesso: () -> Unit,
) {

    var speed by remember {
        mutableStateOf(2f)
    }
    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.check)
    )

    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = startAnimacao,
        speed = speed,
        restartOnPlay = false
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        LottieAnimation(
            composition,
            progress,
            modifier = Modifier
                .fillMaxSize(0.5f)
                .align(Alignment.Center),
            contentScale = ContentScale.Crop
        )
    }

    if (progress == 1f) {
        funcaoSucesso()
    }
}

@Composable
fun AnimationError(
    text: String = stringResource(id = R.string.ocorreu_um_erro),
    size: Dp = 200.dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        InfiniteAnimation(
            modifier = Modifier
                .requiredSize(size),
            R.raw.error, true
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AnimationNaoEncontrado(
    modifier: Modifier,
    text: String = stringResource(id = R.string.dados_nao_encontrados),
    size: Dp = 200.dp
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        InfiniteAnimation(
            modifier = Modifier
                .requiredSize(size),
            R.raw.not_found, true
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}

