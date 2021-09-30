package com.peteralexbizjak.wifiscannerv2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val lightColorPalette = lightColors(
    // Product color definitions
    primary = colorProductPrimary,
    primaryVariant = colorProductPrimaryVariant,
    secondary = colorProductSecondary,
    secondaryVariant = colorProductSecondaryVariant,

    // Backgrounds and surfaces
    background = colorBackgroundLight,
    surface = colorBackgroundLight,

    // Texts on backgrounds, surfaces, primary and secondary
    onBackground = colorTextDark,
    onSurface = colorTextDark,
    onPrimary = colorTextLight,
    onSecondary = colorTextLight
)

private val darkColorPalette = darkColors(
    // Product color definitions (no secondary variant needed here)
    primary = colorProductPrimary,
    primaryVariant = colorProductPrimaryVariant,
    secondary = colorProductSecondary,

    // Backgrounds and surfaces
    background = colorBackgroundDark,
    surface = colorBackgroundDark,

    // Texts on backgrounds, surfaces, primary and secondary
    onBackground = colorTextLight,
    onSurface = colorTextLight,
    onPrimary = colorTextLight,
    onSecondary = colorTextLight
)

@Composable
fun WiFiScannerV2Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) darkColorPalette else lightColorPalette
    MaterialTheme(
        colors = colors,
        shapes = Shapes(
            small = RoundedCornerShape(4.dp),
            medium = RoundedCornerShape(4.dp),
            large = RoundedCornerShape(0.dp)
        ),
        content = content
    )
}