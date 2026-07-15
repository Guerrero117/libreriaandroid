package com.example.prueba

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistroActivity : AppCompatActivity() {

    private lateinit var retrofit: Retrofit
    private lateinit var apiService: MiBackendApiService

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_registro)

        retrofit = Retrofit.Builder()
            .baseUrl("https://libreria-uorh.onrender.com/") // Asegúrate que esta sea tu URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(MiBackendApiService::class.java)

        val etUsuario = findViewById<EditText>(R.id.etRegistroUsuario)
        val etEmail = findViewById<EditText>(R.id.etRegistroEmail) // NUEVO
        val etContrasena = findViewById<EditText>(R.id.etRegistroContrasena)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrarUsuario)
        val tvVolverLogin = findViewById<TextView>(R.id.tvVolverLogin)

        btnRegistrar.setOnClickListener {
            val usuarioTxt = etUsuario.text.toString().trim()
            val emailTxt = etEmail.text.toString().trim() // NUEVO
            val contrasenaTxt = etContrasena.text.toString().trim()

            if (usuarioTxt.isEmpty() || emailTxt.isEmpty() || contrasenaTxt.isEmpty()) {
                Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Enviamos los 3 datos
            val requestData = UserRequest(usuarioTxt, contrasenaTxt, emailTxt)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = apiService.registrarUsuario(requestData)

                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful && response.body()?.exito == true) {
                            Toast.makeText(this@RegistroActivity, "¡Usuario Creado!", Toast.LENGTH_LONG).show()
                            finish()
                        } else {
                            val mensajeError = response.body()?.mensaje ?: "Error en el registro"
                            Toast.makeText(this@RegistroActivity, mensajeError, Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@RegistroActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        tvVolverLogin.setOnClickListener {
            finish()
        }
    }
}