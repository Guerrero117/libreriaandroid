package com.example.prueba.wear.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import com.example.prueba.wear.ui.theme.AccentBlue
import com.example.prueba.wear.ui.theme.AccentGreen
import com.example.prueba.wear.ui.theme.TextSecondary

data class HubOpcion(
    val titulo: String,
    val icono: ImageVector,
    val colorAcento: androidx.compose.ui.graphics.Color,
    val ruta: String
)

private val opciones = listOf(
    HubOpcion("Mis Préstamos", Icons.Filled.Menu, AccentGreen, "prestamos"),
    HubOpcion("Buscar Libro", Icons.Filled.Search, AccentBlue, "buscar")
)

@Composable
fun HubScreen(onNavegar: (String) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        TimeText()
        Vignette(vignettePosition = VignettePosition.TopAndBottom)

        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp, start = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "SmartLib",
                        style = MaterialTheme.typography.title3,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = "Hub de Consultas",
                        style = MaterialTheme.typography.caption2,
                        color = TextSecondary
                    )
                }
            }

            items(opciones) { opcion ->
                Chip(
                    onClick = { onNavegar(opcion.ruta) },
                    label = {
                        Text(text = opcion.titulo, fontWeight = FontWeight.SemiBold)
                    },
                    icon = {
                        Icon(
                            imageVector = opcion.icono,
                            contentDescription = opcion.titulo,
                            modifier = Modifier.size(20.dp),
                            tint = opcion.colorAcento
                        )
                    },
                    colors = ChipDefaults.chipColors(
                        backgroundColor = MaterialTheme.colors.surface
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
