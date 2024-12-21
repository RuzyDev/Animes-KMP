package br.com.arcom.autoriza.android.ui.designsystem.components.text

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import br.com.arcom.autoriza.R
import br.com.arcom.autoriza.android.ui.designsystem.components.toPx

@Composable
fun TextWithLabelHorizontal(
    modifier: Modifier = Modifier,
    text: String?,
    label: String,
    textAlign: TextAlign = TextAlign.Start,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = buildAnnotatedString {
            if (label.isNotEmpty()) {
                withStyle(
                    style.copy(color = contentColor, fontWeight = FontWeight.SemiBold).toSpanStyle()
                ) {
                    append("$label: ")
                }
            }
            withStyle(
                style.copy(color = contentColor).toSpanStyle()
            ) {
                append(text ?: stringResource(id = R.string.nao_encontrado))
            }
        },
        textAlign = textAlign,
        modifier = modifier,
        overflow = overflow,
        maxLines = maxLines,
        lineHeight = style.lineHeight
    )
}

@Composable
fun TextWithLabelVertical(
    modifier: Modifier = Modifier,
    text: String?,
    label: String,
    labelContentColor: Color = MaterialTheme.colorScheme.onSurface,
    textContentColor: Color = MaterialTheme.colorScheme.onSurface,
    align: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier, horizontalAlignment = align) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = labelContentColor,
            lineHeight = MaterialTheme.typography.labelMedium.lineHeight
        )
        Text(
            text = text ?: stringResource(id = R.string.nao_encontrado),
            style = MaterialTheme.typography.titleMedium,
            color = textContentColor,
            lineHeight = MaterialTheme.typography.titleMedium.lineHeight
        )
    }
}

@Composable
fun AutosizeText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = MaterialTheme.colorScheme.onSurface,
    fontWeight: FontWeight = FontWeight.Bold
) {
    BoxWithConstraints(modifier = modifier) {
        val textMeasurer = rememberTextMeasurer()
        val fontSize = style.fontSize
        val textLayoutResult = textMeasurer.measure(text, style)
        val adjustedFontSize = if (textLayoutResult.size.width > maxWidth.toPx()) {
            fontSize * ((maxWidth.toPx() / textLayoutResult.size.width) * 0.9f)
        } else {
            fontSize
        }

        Text(
            text = text,
            style = style,
            maxLines = 1,
            fontSize = adjustedFontSize,
            color = color,
            fontWeight = fontWeight,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun AutosizeText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = MaterialTheme.colorScheme.onSurface,
    fontWeight: FontWeight = FontWeight.Bold
) {
    BoxWithConstraints(modifier = modifier) {
        val textMeasurer = rememberTextMeasurer()
        val fontSize = style.fontSize
        val textLayoutResult = textMeasurer.measure(text, style)
        val adjustedFontSize = if (textLayoutResult.size.width > maxWidth.toPx()) {
            fontSize * ((maxWidth.toPx() / textLayoutResult.size.width) * 0.9f)
        } else {
            fontSize
        }

        Text(
            text = text,
            style = style,
            maxLines = 1,
            fontSize = adjustedFontSize,
            color = color,
            fontWeight = fontWeight,
            overflow = TextOverflow.Ellipsis
        )
    }
}