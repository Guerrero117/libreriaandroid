package com.example.prueba

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BuscarLibrosActivity : AppCompatActivity() {

    private val api = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/books/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(BooksApiService::class.java)

    private var buscarRunnable: Runnable? = null
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_libros)

        val rv = findViewById<RecyclerView>(R.id.rvLibros)
        rv.layoutManager = LinearLayoutManager(this)

        val inputBuscar = findViewById<TextInputEditText>(R.id.etBuscar)

        inputBuscar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                buscarRunnable?.let { handler.removeCallbacks(it) }
                buscarRunnable = Runnable {
                    val query = s.toString()
                    if (query.length > 3) {
                        buscarLibrosEnApi(query)
                    }
                }
                handler.postDelayed(buscarRunnable!!, 500)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun buscarLibrosEnApi(query: String) {
        api.searchBooks(query).enqueue(object : retrofit2.Callback<BookResponse> {
            override fun onResponse(call: retrofit2.Call<BookResponse>, response: retrofit2.Response<BookResponse>) {
                if (response.isSuccessful) {
                    val items = response.body()?.items ?: emptyList()
                    runOnUiThread {
                        val listaMapeada = items.map {
                            Libro(it.volumeInfo.title, it.volumeInfo.authors?.firstOrNull() ?: "Autor Desconocido", it.volumeInfo.imageLinks?.thumbnail ?: "")
                        }
                        val recyclerView = findViewById<RecyclerView>(R.id.rvLibros)

                        recyclerView.adapter = LibroAdapter(listaMapeada) { libroSeleccionado ->
                            guardarEnServidor(libroSeleccionado)
                        }
                    }
                }
            }
            override fun onFailure(call: retrofit2.Call<BookResponse>, t: Throwable) {
                Log.e("API_PRUEBA", "Error: ${t.message}")
            }
        })
    }

    private fun guardarEnServidor(libro: Libro) {
        val sharedPref = getSharedPreferences("SesionUsuario", MODE_PRIVATE)
        val usuario = sharedPref.getString("username", "") ?: ""

        val retrofit = Retrofit.Builder()
            .baseUrl("https://libreria-uorh.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(MiBackendApiService::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // AQUÍ ESTÁ LA CORRECCIÓN: usamos 'libroId' tal cual aparece en tu definición de clase
                val request = FavoritoRequest(usuario = usuario, libroId = libro.titulo, titulo = libro.titulo)
                val response = apiService.agregarFavorito(request)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@BuscarLibrosActivity, "¡Añadido a favoritos!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@BuscarLibrosActivity, "Error al guardar: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@BuscarLibrosActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}