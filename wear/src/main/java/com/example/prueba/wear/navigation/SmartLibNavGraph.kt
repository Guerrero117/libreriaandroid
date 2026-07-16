package com.example.prueba.wear.navigation

import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.prueba.wear.screens.BuscarLibroScreen
import com.example.prueba.wear.screens.FavoritosScreen
import com.example.prueba.wear.screens.HubScreen
import com.example.prueba.wear.screens.PerfilScreen
import com.example.prueba.wear.screens.DetalleLibroScreen

object Rutas {

    const val HUB = "hub"

    const val BUSCAR = "buscar"

    const val FAVORITOS = "favoritos"

    const val PERFIL = "perfil"

    const val DETALLE = "detalle/{id}"

}

@Composable
fun SmartLibNavGraph() {

    val navController = rememberSwipeDismissableNavController()

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = Rutas.HUB
    ) {

        composable(Rutas.HUB) {

            HubScreen(

                onBuscar = {
                    navController.navigate(Rutas.BUSCAR)
                },

                onFavoritos = {
                    navController.navigate(Rutas.FAVORITOS)
                },

                onPerfil = {
                    navController.navigate(Rutas.PERFIL)
                }

            )

        }

        composable(Rutas.BUSCAR) {

            BuscarLibroScreen(

                onLibroSeleccionado = { id ->

                    navController.navigate("detalle/$id")

                }

            )

        }

        composable(Rutas.FAVORITOS) {

            FavoritosScreen()

        }

        composable(Rutas.PERFIL) {

            PerfilScreen()

        }

        composable(Rutas.DETALLE) { backStack ->

            val id = backStack.arguments
                ?.getString("id")
                ?.toIntOrNull() ?: 0

            DetalleLibroScreen(id)

        }

    }

}