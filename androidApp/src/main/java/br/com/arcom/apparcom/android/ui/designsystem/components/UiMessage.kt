package br.com.arcom.apparcom.android.ui.designsystem.components

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import br.com.arcom.apparcom.presentation.util.UiMessage

@Composable
fun UiStateMessage(
    uiState: UiMessage?,
    snackbarHostState: SnackbarHostState,
    clearMessage: (Long) -> Unit
) {
    val keyboard = LocalSoftwareKeyboardController.current
    var confirmMsg by remember {
        mutableStateOf(false)
    }

    if (confirmMsg) {
        if (uiState != null) {
            UiMessageDialog(uiState) {
                confirmMsg = false
                clearMessage(uiState.id)
            }
        }
    }

    LaunchedEffect(uiState) {
        uiState?.let {
            Log.d("Messagem", uiState.message)
            if (it.confirmMsg) {
                confirmMsg = true
            } else {
                keyboard?.hide()
                snackbarHostState.showSnackbar(it.message)
                clearMessage(it.id)
            }
        }
    }
}
