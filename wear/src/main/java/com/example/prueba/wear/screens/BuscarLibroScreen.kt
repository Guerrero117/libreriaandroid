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
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import com.example.prueba.wear.data.LibroUi
import com.example.prueba.wear.data.MockData
import com.example.prueba.wear.ui.theme.AccentGreen
import com.example.prueba.wear.ui.theme.AccentRed
import com.example.prueba.wear.ui.theme.TextSecondary

@Composable
fun BuscarLibroScreen(onVerDetalle: (Int) -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {

        TimeText()

        Vignette(
            vignettePosition = VignettePosition.TopAndBottom
        )

        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = 24.dp,
                bottom = 24.dp,
                start = 6.dp,
                end = 6.dp
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Text(
                    text = "Buscar Libro",
                    style = MaterialTheme.typography.title3,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }

            item {
                Chip(
                    onClick = { },
                    label = {
                        Text("Buscar por voz")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = "Buscar por voz",
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    colors = ChipDefaults.primaryChipColors(),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Text(
                    text = "Resultados recientes",
                    style = MaterialTheme.typography.caption2,
                    color = TextSecondary
                )
            }

            items(MockData.resultadosBusqueda) { libro ->
                LibroResultadoCard(
                    libro = libro,
                    onClick = {
                        onVerDetalle(libro.idLibro)
                    }
                )
            }
        }
    }
}

@Composable
private fun LibroResultadoCard(
    libro: LibroUi,
    onClick: () -> Unit
) {

    val disponible = libro.stock > 0

    val colorStock =
        if (disponible) AccentGreen
        else AccentRed

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(8.dp)
        ) {

            Text(
                text = libro.titulo,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )

            Text(
                text = libro.autor,
                style = MaterialTheme.typography.caption2,
                color = TextSecondary,
                maxLines = 1
            )

            Text(
                text =
                    if (disponible)
                        "Disponible (${libro.stock})"
                    else
                        "Sin stock",
                style = MaterialTheme.typography.caption1,
                color = colorStock
            )
        }
    }
}