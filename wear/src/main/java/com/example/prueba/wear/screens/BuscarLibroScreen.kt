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
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

data class LibroWear(
    val id: Int,
    val titulo: String,
    val autor: String,
    val disponible: Boolean
)

@Composable
fun BuscarLibroScreen(
    onLibroSeleccionado: (Int) -> Unit
) {

    // Datos de ejemplo (después los cambiaremos por Google Books)
    val libros = listOf(
        LibroWear(1, "Harry Potter", "J.K. Rowling", true),
        LibroWear(2, "El Principito", "Antoine de Saint-Exupéry", true),
        LibroWear(3, "1984", "George Orwell", false),
        LibroWear(4, "Don Quijote", "Miguel de Cervantes", true)
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        TimeText()

        ScalingLazyColumn(

            modifier = Modifier.fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center,

            contentPadding = PaddingValues(
                top = 22.dp,
                bottom = 22.dp,
                start = 8.dp,
                end = 8.dp
            )

        ) {

            item {

                Text(
                    text = "Buscar Libro",
                    style = MaterialTheme.typography.title3,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5D4037)
                )

            }

            item {

                Chip(

                    onClick = {
                        // Más adelante activaremos búsqueda por voz
                    },

                    modifier = Modifier.fillMaxWidth(),

                    colors = ChipDefaults.primaryChipColors(
                        backgroundColor = Color.White
                    ),

                    icon = {

                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = null,
                            tint = Color(0xFF5D4037)
                        )

                    },

                    label = {

                        Text(
                            "Buscar por voz",
                            color = Color(0xFF3E2723)
                        )

                    }

                )

            }

            items(libros) { libro ->

                Card(

                    onClick = {
                        onLibroSeleccionado(libro.id)
                    },

                    modifier = Modifier.fillMaxWidth()

                ) {

                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {

                        Text(
                            text = libro.titulo,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3E2723)
                        )

                        Text(
                            text = libro.autor,
                            color = Color(0xFF795548),
                            style = MaterialTheme.typography.caption2
                        )

                        Text(

                            text =
                                if (libro.disponible)
                                    "Disponible"
                                else
                                    "No disponible",

                            color =
                                if (libro.disponible)
                                    Color(0xFF4CAF50)
                                else
                                    Color.Red

                        )

                    }

                }

            }

        }

    }

}