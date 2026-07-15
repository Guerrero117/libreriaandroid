package com.example.prueba

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FavoritosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        val recyclerView = findViewById<RecyclerView>(R.id.rvFavoritos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val sharedPref = getSharedPreferences("SesionUsuario", MODE_PRIVATE)
        val usuario = sharedPref.getString("username", null)

        if (usuario != null) {
            cargarFavoritos(usuario, recyclerView)
        } else {
            Toast.makeText(this, "Error: No se encontró usuario", Toast.LENGTH_SHORT).show()
        }

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }
    }

    private fun cargarFavoritos(usuario: String, recyclerView: RecyclerView) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://libreria-uorh.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(MiBackendApiService::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.obtenerFavoritos(usuario)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val listaRaw = response.body() ?: emptyList()
                        val listaLibros = listaRaw.map { favorito ->
                            Libro(titulo = favorito.titulo, autor = "Favorito guardado", portadaUrl = "")
                        }

                        // CORRECCIÓN: Añadimos { } para cumplir con el requisito del constructor
                        recyclerView.adapter = LibroAdapter(listaLibros) {
                            // En la lista de favoritos no necesitamos hacer nada al hacer clic
                        }

                    } else {
                        Toast.makeText(this@FavoritosActivity, "Error al cargar: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FavoritosActivity, "Error de conexión: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}