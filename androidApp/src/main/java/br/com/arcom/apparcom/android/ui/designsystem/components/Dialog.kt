package br.com.arcom.apparcom.android.ui.designsystem.components

import AppArcomIcons
import Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
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
import br.com.arcom.apparcom.util.format.formatBytes

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

@Composable
fun BaixarAtualizacaoDialog(
    onBaixarClick: (baixarNovamente: Boolean) -> Unit,
    closeDialog: () -> Unit,
    progress: Pair<Long, Long>?,
    versaoAtualMuitoAntiga: Boolean,
    versaoInstalada: Boolean
) {
    val porcentagem = if ((progress?.first ?: 0L) > 0L && (progress?.second ?: 0L) > 0L) {
        (progress!!.first * 100) / progress.second
    }else{
        null
    }
    val enabledButtons = porcentagem == null || porcentagem == 100L

    Dialog(
        onDismissRequest = { if (enabledButtons && !versaoAtualMuitoAntiga) closeDialog() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            Modifier
                .fillMaxWidth(0.9f)
                .background(MaterialTheme.colorScheme.surfaceContainer, RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Row(verticalAlignment = CenterVertically) {
                Box(modifier = Modifier.requiredSize(72.dp)) {
                    if (porcentagem != null) {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.primary,
                            progress = { (porcentagem.toFloat() / 100f) }
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = "Logo app",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .requiredSize(40.dp)
                            .align(Alignment.Center)
                    )
                }
                Column(modifier = Modifier.padding(start = 12.dp)) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = if (porcentagem != null && progress != null && porcentagem != 100L) {
                            "$porcentagem% de ${formatBytes(progress.second)}"
                        } else {
                            stringResource(id = R.string.atualizacao_disponivel)
                        },
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    if (versaoAtualMuitoAntiga) {
                        Text(
                            text = stringResource(id = R.string.versao_muito_antiga),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
            Button(
                onClick = {
                    onBaixarClick(false)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ), modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                enabled = enabledButtons,
                shape = CornerShapeAppArcom
            ) {
                Text(
                    text = stringResource(id = if (versaoInstalada) R.string.instalar else R.string.baixar),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            if (versaoInstalada){
                Text(
                    text = stringResource(id = R.string.baixar_novamente),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                        .clip(CornerShapeAppArcom)
                        .clickable(enabledButtons) {
                            onBaixarClick(true)
                        }
                        .fillMaxWidth()
                        .padding(12.dp),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary.copy( if (enabledButtons) 1f else 0.3f )
                )
            }
        }
    }
}