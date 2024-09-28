package br.com.arcom.sign.designsystem.theme

import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.arcom.sign.designsystem.theme.lightColor

@Composable
fun iconButtonColorAnimesHub() = IconButtonColors(
    containerColor = Color.Transparent,
    contentColor = MaterialTheme.colorScheme.primary,
    disabledContainerColor = Color.Transparent,
    disabledContentColor = MaterialTheme.colorScheme.primary.lightColor()
)