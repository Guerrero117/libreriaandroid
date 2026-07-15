package com.example.prueba

object FavoritosManager {
    // Esta lista guardará los libros en memoria mientras la app esté abierta
    val listaFavoritos = mutableListOf<Libro>()

    fun agregar(libro: Libro) {
        // Evitamos que el mismo libro se agregue dos veces
        if (!listaFavoritos.contains(libro)) {
            listaFavoritos.add(libro)
        }
    }

    fun limpiar() {
        listaFavoritos.clear()
    }
}