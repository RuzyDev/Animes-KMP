package br.com.arcom.autoriza.android.ui.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.arcom.autoriza.android.ui.designsystem.theme.CornerShapeAutoriza

@Composable
fun String.TextButtonAutoriza(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
    onClick: () -> Unit
) {
    val text = this
    TextButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(contentColor = color),
        contentPadding = contentPadding,
        shape = CornerShapeAutoriza
    ) {
        Text(
            text = text, style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}