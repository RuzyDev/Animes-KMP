package br.com.arcom.autoriza.android.ui.designsystem.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.arcom.autoriza.designsystem.theme.Amarelo
import br.com.arcom.autoriza.presentation.util.MessageType
import br.com.arcom.autoriza.presentation.util.UiMessage

@Composable
fun SwipeDismissSnackbar(
    data: SnackbarData,
    onDismiss: (() -> Unit)? = null,
    uiMessage: UiMessage? = null,
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it != SwipeToDismissBoxValue.Settled) {
                data.dismiss()
                onDismiss?.invoke()
            }
            true
        }
    )

    val color = when (uiMessage?.type) {
        MessageType.WARN -> Pair(
            Amarelo,
            Color.White
        )

        MessageType.INFO -> Pair(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.onPrimary
        )

        MessageType.ERROR -> Pair(
            MaterialTheme.colorScheme.error,
            MaterialTheme.colorScheme.onError
        )

        else -> Pair(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.onPrimary
        )
    }

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {},
        content = {
            Snackbar(
                snackbarData = data,
                containerColor = color.first,
                contentColor = color.second
            )
        }
    )
}

@Composable
fun SwipeDismissSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = { hostState.currentSnackbarData?.dismiss() },
    uiMessage: UiMessage?,
    clearMessage: (Long) -> Unit
) {

    LaunchedEffect(uiMessage?.id) {
        uiMessage?.message?.let {
            hostState.showSnackbar(it)
            clearMessage(uiMessage.id)
        }
    }

    SnackbarHost(
        hostState = hostState,
        snackbar = { data ->
            SwipeDismissSnackbar(
                data = data,
                onDismiss = onDismiss,
                uiMessage = uiMessage
            )
        },
        modifier = modifier,
    )
}