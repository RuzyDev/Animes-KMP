package br.com.arcom.autoriza.android.ui.designsystem.components

import AppArcomIcons
import Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import br.com.arcom.autoriza.android.R
import br.com.arcom.autoriza.presentation.util.UiMessage

@Composable
fun UiMessageDialog(
    uiMessage: UiMessage,
    confirmMsg: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = { Text(text = stringResource(id = R.string.alerta)) },
        icon = { AppArcomIcons.ALERTA.Composable() },
        text = { Text(text = uiMessage.message) },
        confirmButton = {
            stringResource(id = R.string.confirmar).TextButtonAppArcom(onClick = confirmMsg)
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = true
        ),
        modifier = Modifier.fillMaxWidth(0.9f),
        containerColor = MaterialTheme.colorScheme.secondary,
        textContentColor = MaterialTheme.colorScheme.onSecondary,
        titleContentColor = MaterialTheme.colorScheme.onSecondary,
        iconContentColor = MaterialTheme.colorScheme.onSecondary
    )
}

@Composable
fun DialogConfirmacao(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.voce_tem_certeza),
    text: String? = null,
    icon: AppArcomIcons? = null,
    closeDialog: () -> Unit,
    confirmClick: () -> Unit
) {

    AlertDialog(onDismissRequest = { closeDialog() },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        text = {
            if (text != null) {
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    confirmClick()
                    closeDialog()
                }, colors = ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = stringResource(id = R.string.confirmar)
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { closeDialog() }) {
                Text(
                    text = stringResource(id = R.string.cancelar),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier,
        icon = if (icon != null) {
            {
                icon.Composable(
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.requiredSize(24.dp)
                )
            }
        } else null)
}