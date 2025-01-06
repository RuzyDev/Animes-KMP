package br.com.arcom.apparcom.android.ui.designsystem.components

import AppArcomIcons
import Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.com.arcom.apparcom.presentation.util.UiMessage
import br.com.arcom.apparcom.android.R
import br.com.arcom.apparcom.android.ui.designsystem.theme.CornerShapeAppArcom
import br.com.arcom.apparcom.designsystem.theme.divider
import br.com.arcom.apparcom.designsystem.theme.lightColor
import br.com.arcom.apparcom.designsystem.theme.secondaryColor

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

@Composable
fun <T> DialogCheck(
    onDismissRequest: () -> Unit,
    itens: List<T>,
    selected: T,
    label: (T) -> String,
    onSelected: (T) -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .clip(CornerShapeAppArcom)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            itens.forEachIndexed { index, item ->
                val selectedItem = item == selected
                Row(
                    Modifier
                        .clickable { onSelected(item) }
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    (if (selectedItem) AppArcomIcons.CHECK else AppArcomIcons.CHECK_BOX).Composable(
                        tint = if (selectedItem) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.secondaryColor(),
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = label(item),
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (selectedItem) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.secondaryColor(),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
                if (index < itens.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.onSurface.divider()
                    )
                }
            }
        }
    }
}