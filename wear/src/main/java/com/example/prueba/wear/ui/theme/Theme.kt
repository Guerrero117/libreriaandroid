package com.example.prueba.wear.ui.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.MaterialTheme

private val SmartLibColorPalette = Colors(
    primary = AccentBlue,
    primaryVariant = AccentBlueDark,
    secondary = AccentGreen,
    secondaryVariant = AccentGreen,
    background = OledBlack,
    surface = SurfaceDark,
    error = AccentRed,
    onPrimary = OledBlack,
    onSecondary = OledBlack,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onError = OledBlack
)

@Composable
fun SmartLibWearTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = SmartLibColorPalette,
        content = content
    )
}
