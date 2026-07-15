package com.example.prueba.wear.navigation

import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.prueba.wear.screens.BuscarLibroScreen
import com.example.prueba.wear.screens.DetalleQrScreen
import com.example.prueba.wear.screens.HubScreen
import com.example.prueba.wear.screens.PrestamosScreen

object Rutas {
    const val HUB = "hub"
    const val PRESTAMOS = "prestamos"
    const val BUSCAR = "buscar"
    const val DETALLE = "detalle/{idLibro}"
}

@Composable
fun SmartLibNavGraph() {
    val navController = rememberSwipeDismissableNavController()

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = Rutas.HUB
    ) {
        composable(Rutas.HUB) {
            HubScreen(onNavegar = { ruta -> navController.navigate(ruta) })
        }
        composable(Rutas.PRESTAMOS) {
            PrestamosScreen()
        }
        composable(Rutas.BUSCAR) {
            BuscarLibroScreen(onVerDetalle = { idLibro ->
                navController.navigate("detalle/$idLibro")
            })
        }
        composable(Rutas.DETALLE) { backStackEntry ->
            val idLibro = backStackEntry.arguments?.getString("idLibro")?.toIntOrNull() ?: -1
            DetalleQrScreen(idLibro = idLibro)
        }
    }
}
