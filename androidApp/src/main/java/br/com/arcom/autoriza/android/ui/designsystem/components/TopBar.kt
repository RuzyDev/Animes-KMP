package br.com.arcom.autoriza.android.ui.designsystem.components

import AutorizaIcons
import Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.arcom.autoriza.android.R
import br.com.arcom.autoriza.designsystem.theme.secondaryColor
import br.com.arcom.autoriza.util.format.getPeriodoDia


@Composable
fun AutorizaTopBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    actions: (@Composable () -> Unit)? = null
) {
    Box(Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 8.dp)) {
        if (onBackClick != null) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                AutorizaIcons.VOLTAR.Composable(
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                )
            }
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.Center),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontWeight = FontWeight.Bold
        )
        if (actions != null) {
            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) { actions() }
        }
    }
}

/**
 * Top Bar mostrado apenas na HomeScreen
 */
@Composable
fun TopBarTopLevel(refresh: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.ola),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimary.secondaryColor()
                )
                Text(
                    text = "${getPeriodoDia()}!",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            AutorizaIcons.ALERTA.Composable(
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.width(18.dp)
            )

        }
    }
}
