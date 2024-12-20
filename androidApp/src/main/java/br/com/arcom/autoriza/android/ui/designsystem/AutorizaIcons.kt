import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.arcom.autoriza.android.R
import br.com.arcom.autoriza.android.ui.designsystem.theme.CornerShapeAppArcom
import br.com.arcom.autoriza.designsystem.theme.lightColor

enum class AppArcomIcons(val icon: TypeIcon, val title: Int) {
    PESQUISA(
        TypeIcon.ImageVectorTypeIcon(Icons.Default.Search),
        R.string.pesquisa
    ),
    CHECK(
        TypeIcon.ImageVectorTypeIcon(Icons.Default.Check),
        R.string.check
    ),
    CLOSE(
        TypeIcon.ImageVectorTypeIcon(Icons.Default.Close),
        R.string.fechar
    ),
    CHECK_CIRCLE(
        TypeIcon.ImageVectorTypeIcon(Icons.Default.CheckCircle),
        R.string.check
    ),
    CHECK_CIRCLE_OUTLINED(
        TypeIcon.ImageVectorTypeIcon(Icons.Outlined.CheckCircle),
        R.string.check
    ),
    PERSON(
        TypeIcon.ImageVectorTypeIcon(Icons.Default.Person),
        R.string.perfil
    ),
    PERSON_OUTLINED(
        TypeIcon.ImageVectorTypeIcon(Icons.Outlined.Person),
        R.string.perfil
    ),
    VOLTAR(
        TypeIcon.ImageVectorTypeIcon(Icons.AutoMirrored.Outlined.KeyboardArrowLeft),
        R.string.voltar
    ),
    AVANCAR(
        TypeIcon.ImageVectorTypeIcon(Icons.AutoMirrored.Outlined.KeyboardArrowRight),
        R.string.avancar
    ),
    ALERTA(
        TypeIcon.ImageVectorTypeIcon(Icons.Default.Warning),
        R.string.alerta
    );
}

/**
 * Estensão de um boolean que retorna um @Composable de um ícone de expandido ou não.
 *
 * @author Ruan
 */
@Composable
fun Boolean.IconExpanded(tint: Color) {
    val icon = if (this) {
        Icons.Default.KeyboardArrowUp
    } else {
        Icons.Default.KeyboardArrowDown
    }
    Icon(
        imageVector = icon,
        contentDescription = Icons.Default.KeyboardArrowUp.name,
        modifier = Modifier.requiredSize(24.dp),
        tint = tint
    )
}

@Composable
fun AppArcomIcons.Composable(
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    when (this.icon) {
        is TypeIcon.ImageVectorTypeIcon -> {
            Icon(
                imageVector = this.icon.imageVector,
                contentDescription = "ImageVectorTypeIcon",
                modifier = modifier,
                tint = tint
            )
        }

        is TypeIcon.DrawableResourceTypeIcon -> {
            Icon(
                painter = painterResource(id = this.icon.id),
                contentDescription = "DrawableResourceTypeIcon",
                modifier = modifier,
                tint = tint
            )
        }

        else -> {}
    }

}

@Composable
fun AppArcomIcons.ExtendedFloating(
    title: Int? = null,
    onClick: () -> Unit
) {
    when (this.icon) {
        is TypeIcon.ImageVectorTypeIcon -> {
            ExtendedFloatingActionButton(
                text = {
                    Text(
                        text = stringResource(title ?: this.title),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                icon = {
                    Icon(
                        imageVector = this.icon.imageVector,
                        contentDescription = "ImageVectorTypeIcon"
                    )
                },
                onClick = onClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )

        }

        is TypeIcon.DrawableResourceTypeIcon -> {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(this.title)) },
                icon = {
                    Icon(
                        painter = painterResource(id = this.icon.id),
                        contentDescription = "DrawableResourceTypeIcon"
                    )
                },
                onClick = onClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }

        else -> {}
    }

}

@Composable
fun AppArcomIcons.Floating(
    onClick: () -> Unit
) {
    when (this.icon) {
        is TypeIcon.ImageVectorTypeIcon -> {
            FloatingActionButton(
                onClick = onClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    imageVector = this.icon.imageVector,
                    contentDescription = "ImageVectorTypeIcon"
                )
            }

        }

        is TypeIcon.DrawableResourceTypeIcon -> {
            FloatingActionButton(
                onClick = onClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    painter = painterResource(id = this.icon.id),
                    contentDescription = "DrawableResourceTypeIcon"
                )
            }
        }

        else -> {}
    }

}


@Composable
fun AppArcomIcons.IconButton(
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    notification: Boolean = false,
    onClick: () -> Unit
) {
    val icon = this.icon
    Box {
        when (icon) {
            is TypeIcon.ImageVectorTypeIcon -> {
                IconButton(
                    onClick = onClick,
                    colors = colors
                ) {
                    Icon(
                        imageVector = icon.imageVector,
                        contentDescription = "ImageVectorTypeIcon"
                    )
                }
            }

            is TypeIcon.DrawableResourceTypeIcon -> {
                IconButton(
                    onClick = onClick,
                    colors = colors
                ) {
                    Icon(
                        painter = painterResource(id = icon.id),
                        contentDescription = "DrawableResourceTypeIcon"
                    )
                }
            }

            else -> {}
        }
        if (notification) {
            Box(
                modifier = Modifier
                    .align(androidx.compose.ui.Alignment.TopEnd)
                    .padding(4.dp)
                    .requiredSize(10.dp)
                    .clip(androidx.compose.foundation.shape.CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary)
            )
        }
    }

}

@Composable
fun AppArcomIcons.ComposableButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val icon = this.icon
    Button(
        onClick = onClick,
        shape = CornerShapeAppArcom,
        contentPadding = PaddingValues(8.dp),
        modifier = modifier.size(42.dp),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.lightColor(),
            disabledContentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        when (icon) {
            is TypeIcon.ImageVectorTypeIcon -> {
                Icon(
                    imageVector = icon.imageVector,
                    contentDescription = "ImageVectorTypeIcon",
                    modifier = Modifier.size(32.dp)
                )
            }

            is TypeIcon.DrawableResourceTypeIcon -> {
                Icon(
                    painter = painterResource(id = icon.id),
                    contentDescription = "ImageVectorTypeIcon",
                    modifier = Modifier.size(26.dp)
                )
            }

            else -> {}
        }

    }
}


@Composable
fun TypeIcon.Composable(
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onBackground
) {
    when (this) {
        is TypeIcon.ImageVectorTypeIcon -> {
            Icon(
                imageVector = this.imageVector,
                contentDescription = "ImageVectorTypeIcon",
                modifier = modifier,
                tint = tint
            )
        }

        is TypeIcon.DrawableResourceTypeIcon -> {
            Icon(
                painter = painterResource(id = this.id),
                contentDescription = "DrawableResourceTypeIcon",
                modifier = modifier,
                tint = tint
            )
        }

        else -> {}
    }

}

sealed class TypeIcon {
    data class ImageVectorTypeIcon(val imageVector: ImageVector) : TypeIcon()
    data class DrawableResourceTypeIcon(@DrawableRes val id: Int) : TypeIcon()
}

sealed class IconTypeCompose {
    data class ImageDrawable(val id: Int) : IconTypeCompose()
    data class IconDrawable(val id: Int) : IconTypeCompose()
}