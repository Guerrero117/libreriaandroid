package com.example.prueba.wear.data

/**
 * NOTA DE DISEÑO:
 * Esta app de Wear OS es únicamente el PROTOTIPO DE INTERFAZ (solo diseño).
 * Los datos que se muestran aquí son de ejemplo (mock), tal como lo pediría
 * un avance de proyecto. La versión final consumirá la misma API que ya usa
 * la app de Smartphone (por ejemplo GET /api/prestamos/usuario/123 y
 * GET /api/libros/buscar?titulo=...), reemplazando estas listas por la
 * respuesta real de Retrofit/OkHttp.
 */

data class PrestamoUi(
    val idPrestamo: Int,
    val titulo: String,
    val diasRestantes: Int,
    val estatus: String // "Activo" o "Vencido"
)

data class LibroUi(
    val idLibro: Int,
    val titulo: String,
    val autor: String,
    val stock: Int
)

object MockData {
    val prestamos = listOf(
        PrestamoUi(1, "Cien años de soledad", 2, "Activo"),
        PrestamoUi(2, "El principito", 0, "Vencido"),
        PrestamoUi(3, "1984", 5, "Activo")
    )

    val resultadosBusqueda = listOf(
        LibroUi(10, "Rayuela", "Julio Cortázar", 3),
        LibroUi(11, "Pedro Páramo", "Juan Rulfo", 0),
        LibroUi(12, "La sombra del viento", "Carlos Ruiz Zafón", 1)
    )
}
