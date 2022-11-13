package com.bojanvilic.crvenazvezdainfo.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when {
        dynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        dynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> darkThemeColors
        else -> lightThemeColors
    }

    MaterialTheme(
        colorScheme = colors,
        shapes = AppShapes,
        content = content
    )
}

private val lightThemeColors = lightColorScheme(
    primary = PrimaryRed,
    primaryContainer = PrimaryRedVariant,
    secondary = PrimaryAccent
)

private val darkThemeColors = darkColorScheme(
    primary = PrimaryRed,
    primaryContainer = PrimaryRedVariant,
    secondary = PrimaryAccent
)