package com.example.prueba.wear.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.*

data class MenuWear(
    val titulo: String,
    val icono: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun HubScreen(
    onBuscar: () -> Unit,
    onFavoritos: () -> Unit,
    onPerfil: () -> Unit
) {

    val opciones = listOf(
        MenuWear("Buscar Libros", Icons.Default.Book),
        MenuWear("Favoritos", Icons.Default.Favorite),
        MenuWear("Mi Perfil", Icons.Default.Person)
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
                    text = "SMARTLIB",
                    style = MaterialTheme.typography.title2,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5D4037)
                )

            }

            item {

                Text(
                    text = "Biblioteca Virtual",
                    style = MaterialTheme.typography.caption2,
                    color = Color(0xFF795548)
                )

            }

            items(opciones) { opcion ->

                Chip(

                    modifier = Modifier.fillMaxWidth(),

                    onClick = {

                        when (opcion.titulo) {

                            "Buscar Libros" -> onBuscar()

                            "Favoritos" -> onFavoritos()

                            "Mi Perfil" -> onPerfil()

                        }

                    },

                    colors = ChipDefaults.primaryChipColors(
                        backgroundColor = Color.White
                    ),

                    icon = {

                        Icon(
                            imageVector = opcion.icono,
                            contentDescription = opcion.titulo,
                            tint = Color(0xFF5D4037),
                            modifier = Modifier.size(22.dp)
                        )

                    },

                    label = {

                        Text(
                            text = opcion.titulo,
                            color = Color(0xFF3E2723),
                            fontWeight = FontWeight.Bold
                        )

                    }

                )

            }

        }

    }

}