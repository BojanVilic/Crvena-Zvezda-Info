package com.bojanvilic.crvenazvezdainfo.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when {
//        dynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
//        dynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> darkThemeColors
        else -> lightThemeColors
    }

    MaterialTheme(
        colorScheme = colors,
        shapes = AppShapes,
        content = content,
        typography = AppTypography
    )
}

private val lightThemeColors = lightColorScheme(
    primary = light_theme_primary,
//    onPrimary = md_theme_light_onPrimary,
//    primaryContainer = md_theme_light_primaryContainer,
//    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = light_theme_secondary,
//    onSecondary = md_theme_light_onSecondary,
//    secondaryContainer = md_theme_light_secondaryContainer,
//    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = light_theme_tertiary,
//    onTertiary = md_theme_light_onTertiary,
//    tertiaryContainer = md_theme_light_tertiaryContainer,
//    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = light_theme_error,
    errorContainer = light_theme_errorContainer,
    onError = light_theme_onError,
    onErrorContainer = light_theme_onErrorContainer,
    background = light_theme_background,
    onBackground = light_theme_onBackground,
//    surface = md_theme_light_surface,
//    onSurface = light_theme_onSurface,
    surfaceVariant = light_theme_surfaceVariant,
    onSurfaceVariant = light_theme_onSurfaceVariant,
//    outline = md_theme_light_outline,
//    inverseOnSurface = md_theme_light_inverseOnSurface,
//    inverseSurface = md_theme_light_inverseSurface,
//    inversePrimary = md_theme_light_inversePrimary,
//    surfaceTint = md_theme_light_surfaceTint
)

private val darkThemeColors = darkColorScheme(
    primary = dark_theme_primary,
    secondary = dark_theme_secondary,
    tertiary = dark_theme_tertiary,
    error = dark_theme_error,
    errorContainer = dark_theme_errorContainer,
    onError = dark_theme_onError,
    onErrorContainer = dark_theme_onErrorContainer,
    background = dark_theme_background,
    onBackground = dark_theme_onBackground,
    surfaceVariant = dark_theme_surfaceVariant,
    onSurfaceVariant = dark_theme_onSurfaceVariant,
    inverseSurface = dark_theme_inverseSurface,
    inverseOnSurface = dark_theme_inverseOnSurface
)