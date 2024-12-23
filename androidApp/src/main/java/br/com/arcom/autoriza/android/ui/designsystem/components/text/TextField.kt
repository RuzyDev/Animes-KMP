package br.com.arcom.autoriza.ui.designsystem.components.text

import AppArcomIcons
import Composable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import br.com.arcom.atomx.ui.designsystem.util.conditional
import br.com.arcom.autoriza.R
import br.com.arcom.autoriza.android.ui.designsystem.theme.CornerShapeAppArcom
import br.com.arcom.autoriza.android.utils.conditional
import br.com.arcom.autoriza.android.utils.shake
import br.com.arcom.autoriza.designsystem.theme.lightColor
import br.com.arcom.autoriza.designsystem.theme.secondaryColor
import br.com.arcom.autoriza.ui.designsystem.icon.AppArcomIcon
import br.com.arcom.autoriza.ui.designsystem.icon.Composable
import br.com.arcom.autoriza.ui.designsystem.theme.CornerShapeAppArcom
import br.com.arcom.autoriza.ui.designsystem.theme.lightColor
import br.com.arcom.autoriza.util.format.isNumberOrEmpty
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppArcomTextField(
    valueState: MutableFieldStringRemember,
    modifier: Modifier = Modifier,
    label: String? = null,
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
    ),
    keyboardActions: AppArcomKeyboardActions = AppArcomKeyboardActions(),
    enabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLength: Int = Int.MAX_VALUE,
    maxLines: Int = Int.MAX_VALUE,
    placeholder: String? = null,
    readOnly: Boolean = false,
    onlyNumbers: Boolean = false
) {
    AppArcomTextField(
        setText = { valueState.setValue(it) },
        text = valueState.value,
        modifier = modifier,
        label = label,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        enabled = enabled,
        visualTransformation = visualTransformation,
        maxLength = maxLength,
        maxLines = maxLines,
        placeholder = placeholder,
        readOnly = readOnly,
        isError = valueState.error,
        onlyNumbers = onlyNumbers
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppArcomTextField(
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    setText: (String) -> Unit,
    text: String,
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
    ),
    keyboardActions: AppArcomKeyboardActions = AppArcomKeyboardActions(),
    isError: Boolean = false,
    enabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLength: Int = Int.MAX_VALUE,
    maxLines: Int = Int.MAX_VALUE,
    readOnly: Boolean = false,
    onlyNumbers: Boolean = false,
    icon: AppArcomIcons? = null,
    iconClick: (() -> Unit)? = null
) {
    val keyboard = LocalSoftwareKeyboardController.current
    val focusResquest = LocalFocusManager.current
    var error by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(isError) {
        if (isError) {
            error = true
            delay(200)
            error = false
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.height(IntrinsicSize.Min)
    ) {
        label?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface.secondaryColor(),
                modifier = Modifier,
                fontWeight = FontWeight.SemiBold
            )
        }
        BasicTextField(
            value = text, onValueChange = {
                if ((!onlyNumbers || it.isNumberOrEmpty()) && it.length <= maxLength) setText(it)
            },
            modifier = Modifier
                .fillMaxHeight()
                .shake(error),
            enabled = enabled,
            readOnly = readOnly,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                    if (enabled) 1f else 0.3f
                )
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions.actions {
                if (!focusResquest.moveFocus(FocusDirection.Next)) {
                    keyboard?.hide()
                }
            },
            singleLine = singleLine,
            maxLines = maxLines,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            decorationBox = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(CornerShapeAppArcom)
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .conditional(isFocused || isError) {
                            this.border(
                                BorderStroke(
                                    (1.5).dp,
                                    if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                                ),
                                CornerShapeAppArcom
                            )
                        }
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (text.isEmpty() && placeholder != null) {
                        Text(
                            text = placeholder, style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.lightColor()
                        )
                    } else {
                        it()
                    }
                    icon?.Composable(
                        modifier = Modifier
                            .size(32.dp)
                            .clickable(
                                indication = null,
                                interactionSource = null,
                                onClick = { iconClick?.invoke() }
                            ),
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            },
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface)
        )
    }
}

@Composable
fun AppArcomTextFieldPesquisa(
    modifier: Modifier = Modifier,
    setText: (String) -> Unit,
    text: String,
    singleLine: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
    enabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLength: Int = Int.MAX_VALUE,
    maxLines: Int = Int.MAX_VALUE,
    readOnly: Boolean = false,
    onlyNumbers: Boolean = false,
    onClick: ((String) -> Unit)? = null
) {
    val keyboard = LocalSoftwareKeyboardController.current
    val focusResquest = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    BasicTextField(
        value = text, onValueChange = {
            if ((!onlyNumbers || it.isNumberOrEmpty()) && it.length <= maxLength) setText(it)
        },
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(onSearch = {
            if (!focusResquest.moveFocus(FocusDirection.Next)) {
                keyboard?.hide()
                onClick?.invoke(text)
            }
        }),
        singleLine = singleLine,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        decorationBox = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(CornerShapeAppArcom)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .conditional(isFocused || isError) {
                        this.border(
                            BorderStroke(
                                (1.5).dp,
                                if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                            ),
                            CornerShapeAppArcom
                        )
                    }
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AppArcomIcons.PESQUISA.Composable(
                    tint = MaterialTheme.colorScheme.onSurface.lightColor(),
                    modifier = Modifier.requiredSize(18.dp)
                )
                if (text.isEmpty() && !isFocused) {
                    Text(
                        text = stringResource(id = R.string.pesquise_aqui),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.lightColor()
                    )
                } else {
                    it()
                }
            }
        },
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSecondaryContainer)
    )
}

class AppArcomKeyboardActions(
    val onDone: (() -> Unit)? = null,
    val onGo: (() -> Unit)? = null,
    val onNext: (() -> Unit)? = null,
    val onPrevious: (() -> Unit)? = null,
    val onSearch: (() -> Unit)? = null,
    val onSend: (() -> Unit)? = null
) {
    fun actions(action: () -> Unit): KeyboardActions {
        return KeyboardActions(
            onDone = {
                action()
                onDone?.invoke()
            },
            onGo = {
                action()
                onGo?.invoke()
            },
            onNext = {
                action()
                onNext?.invoke()
            },
            onPrevious = {
                action()
                onPrevious?.invoke()
            },
            onSearch = {
                action()
                onSearch?.invoke()
            },
            onSend = {
                action()
                onSend?.invoke()
            },
        )
    }
}

