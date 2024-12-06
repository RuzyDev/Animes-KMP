package br.com.arcom.autoriza.android.ui.designsystem.components

import AutorizaIcons
import Composable
import ComposableButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.arcom.autoriza.android.R
import br.com.arcom.autoriza.designsystem.theme.secondaryColor
import br.com.arcom.autoriza.util.format.getPeriodoDia

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutorizaTopBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title, style = MaterialTheme.typography.titleMedium) },
        navigationIcon = {
            if (onBackClick != null) {
                AutorizaIcons.VOLTAR.ComposableButton(onClick = onBackClick)
            }
        },
        actions = { actions?.invoke() },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
        )
    )
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
