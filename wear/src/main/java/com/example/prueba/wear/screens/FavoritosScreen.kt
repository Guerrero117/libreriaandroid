package com.example.prueba.wear.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.*

data class FavoritoWear(
    val titulo:String,
    val autor:String
)

@Composable
fun FavoritosScreen(){

    //Después estos datos vendrán de tu API

    val favoritos=listOf(

        FavoritoWear(
            "Harry Potter",
            "J.K. Rowling"
        ),

        FavoritoWear(
            "El Principito",
            "Antoine de Saint-Exupéry"
        ),

        FavoritoWear(
            "1984",
            "George Orwell"
        )

    )

    Box(
        modifier=Modifier.fillMaxSize()
    ){

        TimeText()

        ScalingLazyColumn(

            modifier=Modifier.fillMaxSize(),

            horizontalAlignment=Alignment.CenterHorizontally,

            verticalArrangement=Arrangement.Center,

            contentPadding= PaddingValues(
                top=22.dp,
                bottom=22.dp,
                start=8.dp,
                end=8.dp
            )

        ){

            item{

                Text(

                    text="Mis Favoritos",

                    style=MaterialTheme.typography.title3,

                    fontWeight= FontWeight.Bold,

                    color= Color(0xFF5D4037)

                )

            }

            items(favoritos){ libro->

                Card(

                    onClick={},

                    modifier=Modifier.fillMaxWidth()

                ){

                    Column(

                        modifier=Modifier.padding(10.dp)

                    ){

                        Row(
                            verticalAlignment=Alignment.CenterVertically
                        ){

                            Icon(

                                imageVector=Icons.Default.Favorite,

                                contentDescription=null,

                                tint= Color.Red,

                                modifier=Modifier.size(18.dp)

                            )

                            Spacer(modifier=Modifier.width(6.dp))

                            Text(

                                text=libro.titulo,

                                fontWeight=FontWeight.Bold,

                                color=Color(0xFF3E2723)

                            )

                        }

                        Spacer(modifier=Modifier.height(4.dp))

                        Text(

                            text=libro.autor,

                            color=Color(0xFF795548),

                            style=MaterialTheme.typography.caption2

                        )

                    }

                }

            }

        }

    }

}