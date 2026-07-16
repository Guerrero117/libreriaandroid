package com.example.prueba.wear.ui.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.MaterialTheme

private val SmartLibColors = Colors(
    primary = CafePrincipal,
    primaryVariant = CafeOscuro,
    secondary = CafeClaro,
    secondaryVariant = CafePrincipal,

    background = Fondo,
    surface = Blanco,

    error = Rojo,

    onPrimary = Blanco,
    onSecondary = Blanco,
    onBackground = TextoPrincipal,
    onSurface = TextoPrincipal,
    onError = Blanco
)

@Composable
fun SmartLibWearTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = SmartLibColors,
        content = content
    )
}