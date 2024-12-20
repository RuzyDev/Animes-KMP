package br.com.arcom.autoriza.designsystem.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import br.com.arcom.autoriza.android.ui.designsystem.theme.AppArcomTypography


private val LightColors = lightColorScheme(
    primary = light_primary,
    onPrimary = light_onPrimary,
    primaryContainer = light_primaryContainer,
    onPrimaryContainer = light_onPrimaryContainer,
    secondary = light_secondary,
    onSecondary = light_onSecondary,
    secondaryContainer = light_secondaryContainer,
    onSecondaryContainer = light_onSecondaryContainer,
    tertiary = light_tertiary,
    onTertiary = light_onTertiary,
    tertiaryContainer = light_tertiaryContainer,
    onTertiaryContainer = light_onTertiaryContainer,
    error = light_error,
    errorContainer = light_errorContainer,
    onError = light_onError,
    onErrorContainer = light_onErrorContainer,
    background = light_background,
    onBackground = light_onBackground,
    surface = light_surface,
    onSurface = light_onSurface,
    surfaceVariant = light_surfaceVariant,
    onSurfaceVariant = light_onSurfaceVariant,
    outline = light_outline,
    inverseOnSurface = light_inverseOnSurface,
    inverseSurface = light_inverseSurface,
    inversePrimary = light_inversePrimary,
    surfaceTint = light_surfaceTint,
    outlineVariant = light_outlineVariant,
    scrim = light_scrim,
)


private val DarkColors = darkColorScheme(
    primary = dark_primary,
    onPrimary = dark_onPrimary,
    primaryContainer = dark_primaryContainer,
    onPrimaryContainer = dark_onPrimaryContainer,
    secondary = dark_secondary,
    onSecondary = dark_onSecondary,
    secondaryContainer = dark_secondaryContainer,
    onSecondaryContainer = dark_onSecondaryContainer,
    tertiary = dark_tertiary,
    onTertiary = dark_onTertiary,
    tertiaryContainer = dark_tertiaryContainer,
    onTertiaryContainer = dark_onTertiaryContainer,
    error = dark_error,
    errorContainer = dark_errorContainer,
    onError = dark_onError,
    onErrorContainer = dark_onErrorContainer,
    background = dark_background,
    onBackground = dark_onBackground,
    surface = dark_surface,
    onSurface = dark_onSurface,
    surfaceVariant = dark_surfaceVariant,
    onSurfaceVariant = dark_onSurfaceVariant,
    outline = dark_outline,
    inverseOnSurface = dark_inverseOnSurface,
    inverseSurface = dark_inverseSurface,
    inversePrimary = dark_inversePrimary,
    surfaceTint = dark_surfaceTint,
    outlineVariant = dark_outlineVariant,
    scrim = dark_scrim,
)

private fun lightColorsDynamic(palette: DynamicPalette) = lightColorScheme(
    primary = palette.primary40,
    onPrimary = palette.primary100,
    primaryContainer = palette.primary90,
    onPrimaryContainer = palette.primary10,
    inversePrimary = palette.primary80,
    secondary = palette.secondary40,
    onSecondary = palette.secondary100,
    secondaryContainer = palette.secondary90,
    onSecondaryContainer = palette.secondary10,
    tertiary = palette.tertiary40,
    onTertiary = palette.tertiary100,
    tertiaryContainer = palette.tertiary90,
    onTertiaryContainer = palette.tertiary10,
    background = palette.neutralVariant98,
    onBackground = palette.neutralVariant10,
    surface = palette.neutralVariant98,
    onSurface = palette.neutralVariant10,
    surfaceVariant = palette.neutralVariant90,
    onSurfaceVariant = palette.neutralVariant30,
    inverseSurface = palette.neutralVariant20,
    inverseOnSurface = palette.neutralVariant95,
    outline = palette.neutralVariant50,
    outlineVariant = palette.neutralVariant80,
    scrim = palette.neutralVariant0,
    surfaceBright = palette.neutralVariant98,
    surfaceDim = palette.neutralVariant87,
    surfaceContainer = palette.neutralVariant94,
    surfaceContainerHigh = palette.neutralVariant92,
    surfaceContainerHighest = palette.neutralVariant90,
    surfaceContainerLow = palette.neutralVariant96,
    surfaceContainerLowest = palette.neutralVariant100,
    surfaceTint = palette.primary40,
)

private fun darkColorsDynamic(palette: DynamicPalette) = darkColorScheme(
    primary = palette.primary80,
    onPrimary = palette.primary20,
    primaryContainer = palette.primary30,
    onPrimaryContainer = palette.primary90,
    inversePrimary = palette.primary40,
    secondary = palette.secondary80,
    onSecondary = palette.secondary20,
    secondaryContainer = palette.secondary30,
    onSecondaryContainer = palette.secondary90,
    tertiary = palette.tertiary80,
    onTertiary = palette.tertiary20,
    tertiaryContainer = palette.tertiary30,
    onTertiaryContainer = palette.tertiary90,
    background = palette.neutralVariant6,
    onBackground = palette.neutralVariant90,
    surface = palette.neutralVariant6,
    onSurface = palette.neutralVariant90,
    surfaceVariant = palette.neutralVariant30,
    onSurfaceVariant = palette.neutralVariant80,
    inverseSurface = palette.neutralVariant90,
    inverseOnSurface = palette.neutralVariant20,
    outline = palette.neutralVariant60,
    outlineVariant = palette.neutralVariant30,
    scrim = palette.neutralVariant0,
    surfaceBright = palette.neutralVariant24,
    surfaceDim = palette.neutralVariant6,
    surfaceContainer = palette.neutralVariant12,
    surfaceContainerHigh = palette.neutralVariant17,
    surfaceContainerHighest = palette.neutralVariant22,
    surfaceContainerLow = palette.neutralVariant10,
    surfaceContainerLowest = palette.neutralVariant4,
    surfaceTint = palette.primary80,
)

@Composable
fun AppArcomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    isDynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val dynamicColor = if(isDynamicColor && supportsDynamicTheming()) dynamicTonalPalette(context) else null
    val myColorScheme = when {
        (dynamicColor != null) && darkTheme -> {
            darkColorsDynamic(dynamicColor)
        }
        (dynamicColor != null) && !darkTheme -> {
            lightColorsDynamic(dynamicColor)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = myColorScheme,
        typography = AppArcomTypography,
        content = content
    )
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S