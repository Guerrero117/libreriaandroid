package com.example.prueba.wear.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import com.example.prueba.wear.data.MockData
import com.example.prueba.wear.data.PrestamoUi
import com.example.prueba.wear.ui.theme.AccentGreen
import com.example.prueba.wear.ui.theme.AccentRed

@Composable
fun PrestamosScreen() {

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
                    text = "Mis Préstamos",
                    style = MaterialTheme.typography.title3,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }

            items(MockData.prestamos) { prestamo ->
                PrestamoCard(prestamo)
            }
        }
    }
}

@Composable
private fun PrestamoCard(prestamo: PrestamoUi) {

    val vencido = prestamo.estatus == "Vencido"

    val colorEstado =
        if (vencido)
            AccentRed
        else
            AccentGreen

    Card(
        onClick = {},
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(8.dp)
        ) {

            Text(
                text = prestamo.titulo,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2
            )

            Text(
                text =
                    if (vencido)
                        "Vencido"
                    else
                        "Quedan ${prestamo.diasRestantes} día(s)",
                style = MaterialTheme.typography.caption1,
                color = colorEstado
            )
        }
    }
}