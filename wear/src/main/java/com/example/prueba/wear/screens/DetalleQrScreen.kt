package com.example.prueba.wear.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.example.prueba.wear.data.MockData
import com.example.prueba.wear.ui.theme.AccentGreen
import com.example.prueba.wear.ui.theme.TextSecondary
import kotlin.random.Random

@Composable
fun DetalleQrScreen(idLibro: Int) {
    val libro = MockData.resultadosBusqueda.find { it.idLibro == idLibro }

    Box(modifier = Modifier.fillMaxSize()) {
        TimeText()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = libro?.titulo ?: "Libro",
                style = MaterialTheme.typography.caption1,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.onBackground,
                maxLines = 1
            )

            QrMockCode(modifier = Modifier
                .size(90.dp)
                .padding(top = 6.dp, bottom = 6.dp))

            Text(
                text = "Muestra este código\nen sucursal",
                style = MaterialTheme.typography.caption2,
                color = TextSecondary
            )
            Text(
                text = "Reservado",
                style = MaterialTheme.typography.caption2,
                fontWeight = FontWeight.Bold,
                color = AccentGreen
            )
        }
    }
}

/**
 * Representación visual de un código QR (mock estático para el prototipo de diseño).
 * En la versión final, este componente se sustituirá por un generador real de QR
 * a partir del id de préstamo devuelto por la API.
 */
@Composable
private fun QrMockCode(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val cells = 7
        val cellSize = size.minDimension / cells
        drawRect(color = Color.White, size = size)

        val random = Random(seed = 42)
        for (row in 0 until cells) {
            for (col in 0 until cells) {
                val isCornerBlock =
                    (row < 2 && col < 2) || (row < 2 && col > cells - 3) || (row > cells - 3 && col < 2)
                val filled = isCornerBlock || random.nextBoolean()
                if (filled) {
                    drawRect(
                        color = Color.Black,
                        topLeft = androidx.compose.ui.geometry.Offset(
                            x = col * cellSize + cellSize * 0.08f,
                            y = row * cellSize + cellSize * 0.08f
                        ),
                        size = androidx.compose.ui.geometry.Size(
                            cellSize * 0.84f,
                            cellSize * 0.84f
                        )
                    )
                }
            }
        }
    }
}
