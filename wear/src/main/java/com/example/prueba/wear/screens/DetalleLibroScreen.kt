package com.example.prueba.wear.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*

@Composable
fun DetalleLibroScreen(idLibro: Int) {

    // Datos temporales (después vendrán de Google Books)

    val titulo = when(idLibro){

        1 -> "Harry Potter"

        2 -> "El Principito"

        3 -> "1984"

        else -> "Don Quijote"

    }

    val autor = when(idLibro){

        1 -> "J.K. Rowling"

        2 -> "Antoine de Saint-Exupéry"

        3 -> "George Orwell"

        else -> "Miguel de Cervantes"

    }

    val disponible = idLibro != 3

    Box(
        modifier = Modifier.fillMaxSize()
    ){

        TimeText()

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center

        ){

            Icon(

                imageVector = Icons.Default.Book,

                contentDescription = null,

                tint = Color(0xFF5D4037),

                modifier = Modifier.size(42.dp)

            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(

                text = titulo,

                style = MaterialTheme.typography.title3,

                fontWeight = FontWeight.Bold,

                color = Color(0xFF3E2723)

            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(

                text = autor,

                style = MaterialTheme.typography.caption2,

                color = Color(0xFF795548)

            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(

                text =
                    if(disponible)
                        "Disponible"
                    else
                        "No disponible",

                color =
                    if(disponible)
                        Color(0xFF4CAF50)
                    else
                        Color.Red,

                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(15.dp))

            Chip(

                onClick = {

                    // Después abrirá la app del teléfono

                },

                modifier = Modifier.fillMaxWidth(),

                colors = ChipDefaults.primaryChipColors(
                    backgroundColor = Color.White
                ),

                icon = {

                    Icon(

                        imageVector = Icons.Default.PhoneAndroid,

                        contentDescription = null,

                        tint = Color(0xFF5D4037)

                    )

                },

                label = {

                    Text(

                        "Abrir en teléfono",

                        color = Color(0xFF3E2723)

                    )

                }

            )

        }

    }

}