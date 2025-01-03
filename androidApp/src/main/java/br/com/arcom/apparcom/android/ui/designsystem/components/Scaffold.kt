package br.com.arcom.apparcom.android.ui.designsystem.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.arcom.apparcom.presentation.util.UiMessage

@Composable
fun AppArcomScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    onBackClick: (() -> Unit)? = null,
    uiMessage: UiMessage?,
    loading: Boolean = false,
    clearMessage: (Long) -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    if (onBackClick != null) BackHandler(onBack = onBackClick)
    val snackbarHostState = remember { SnackbarHostState() }

    UiStateMessage(
        uiState = uiMessage,
        snackbarHostState = snackbarHostState,
        clearMessage = clearMessage
    )

    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        snackbarHost = {
            SwipeDismissSnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .fillMaxWidth(),
                clearMessage = clearMessage,
                uiMessage = uiMessage
            )
        },
        contentWindowInsets = contentWindowInsets
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
    LoadingScreen(loading)
}

@Composable
fun AppArcomScaffoldWithAnimationSuccess(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    onBackClick: () -> Unit,
    uiMessage: UiMessage?,
    clearMessage: (Long) -> Unit,
    success: Boolean,
    onSuccess: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Crossfade(success, label = "") { s ->
        if (!s) {
            AppArcomScaffold(
                modifier = modifier,
                topBar = topBar,
                bottomBar = bottomBar,
                floatingActionButton = floatingActionButton,
                floatingActionButtonPosition = floatingActionButtonPosition,
                contentWindowInsets = contentWindowInsets,
                onBackClick = onBackClick,
                clearMessage = clearMessage,
                content = content,
                uiMessage = uiMessage
            )
        } else {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                EnvioAnimacaoSucess(
                    startAnimacao = true,
                    funcaoSucesso = {
                        onSuccess()
                    }
                )
            }
        }
    }
}

@Composable
fun LoadingScreen(enabled: Boolean) {

    val interactionSource = remember { MutableInteractionSource() }

    if (enabled) {
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface.copy(0.5f))
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {}
        ) {

            LoadingAnimacao(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}