package com.example.prueba

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {

    private lateinit var ctUsuario: EditText
    private lateinit var ctContraseña: EditText
    private lateinit var btnAcceder: Button
    private lateinit var btnIrARegistro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ctUsuario = findViewById(R.id.ctUsuario)
        ctContraseña = findViewById(R.id.ctContraseña)
        btnAcceder = findViewById(R.id.btnAcceder)
        btnIrARegistro = findViewById(R.id.btnIrARegistro)

        btnAcceder.setOnClickListener {
            val usuario = ctUsuario.text.toString().trim()
            val contrasena = ctContraseña.text.toString().trim()

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            realizarLogin(usuario, contrasena)
        }

        // Lógica para ir a la pantalla de Registro
        btnIrARegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun realizarLogin(usuario: String, contrasena: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val client = OkHttpClient.Builder()
                    .connectTimeout(40, TimeUnit.SECONDS)
                    .readTimeout(40, TimeUnit.SECONDS)
                    .build()

                val jsonBody = JSONObject().apply {
                    put("usuario", usuario)
                    put("contrasena", contrasena)
                }

                val requestBody = jsonBody.toString().toRequestBody("application/json".toMediaType())

                val request = Request.Builder()
                    .url("https://libreria-uorh.onrender.com/api/login")
                    .post(requestBody)
                    .build()

                val response = client.newCall(request).execute()
                val responseData = response.body?.string()

                if (response.isSuccessful && responseData != null) {
                    val jsonResponse = JSONObject(responseData)
                    val existe = jsonResponse.getBoolean("existe")

                    withContext(Dispatchers.Main) {
                        if (existe) {
                            // --- AQUÍ ESTÁ LO QUE AGREGAMOS ---
                            // Guardamos el usuario para que FavoritosActivity sepa quién es
                            val sharedPref = getSharedPreferences("SesionUsuario", MODE_PRIVATE)
                            sharedPref.edit().putString("username", usuario).apply()
                            // ----------------------------------

                            Toast.makeText(this@LoginActivity, "Bienvenido", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, MenuActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@LoginActivity, "Error de servidor: ${response.code}", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@LoginActivity, "Error de conexión: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}