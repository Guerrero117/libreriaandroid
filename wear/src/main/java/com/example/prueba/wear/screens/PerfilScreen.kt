package com.example.prueba.wear.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*

@Composable
fun PerfilScreen() {

    // Después estos datos vendrán de tu API

    val nombre = "Tania Portillo"

    val correo = "tania@gmail.com"

    val rol = "Administrador"

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        TimeText()

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center

        ) {

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color(0xFF5D4037),
                modifier = Modifier.size(46.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = nombre,
                style = MaterialTheme.typography.title3,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3E2723)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = rol,
                color = Color(0xFF795548),
                style = MaterialTheme.typography.caption2
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = Color(0xFF5D4037)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column {

                        Text(
                            text = "Correo",
                            color = Color(0xFF795548),
                            style = MaterialTheme.typography.caption2
                        )

                        Text(
                            text = correo,
                            color = Color(0xFF3E2723)
                        )

                    }

                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            Chip(

                onClick = {

                    // Después cerraremos sesión

                },

                modifier = Modifier.fillMaxWidth(),

                colors = ChipDefaults.secondaryChipColors(),

                icon = {

                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = null
                    )

                },

                label = {

                    Text("Cerrar sesión")

                }

            )

        }

    }

}