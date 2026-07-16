package com.example.prueba

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class MenuActivity : AppCompatActivity() {

    private lateinit var tvSaludo: TextView
    private lateinit var tvRol: TextView

    private lateinit var cardBuscar: MaterialCardView
    private lateinit var cardFavoritos: MaterialCardView
    private lateinit var cardPerfil: MaterialCardView
    private lateinit var cardUsuarios: MaterialCardView

    private lateinit var btnCerrarSesion: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        tvSaludo = findViewById(R.id.tvSaludo)
        tvRol = findViewById(R.id.tvRol)

        cardBuscar = findViewById(R.id.cardBuscar)
        cardFavoritos = findViewById(R.id.cardFavoritos)
        cardPerfil = findViewById(R.id.cardPerfil)
        cardUsuarios = findViewById(R.id.cardUsuarios)

        btnCerrarSesion = findViewById(R.id.btnCerrarSesion)

        cargarDatosUsuario()

        eventos()
    }

    private fun cargarDatosUsuario() {

        val shared = getSharedPreferences("SesionUsuario", MODE_PRIVATE)

        val usuario = shared.getString("username", "Usuario")
        val rol = shared.getString("rol", "Operador")

        tvSaludo.text = "Hola, $usuario 👋"
        tvRol.text = rol

        // Si no es administrador ocultamos la opción

        if (rol.equals("Operador", true)) {

            cardUsuarios.visibility = android.view.View.GONE

        }

    }

    private fun eventos() {

        cardBuscar.setOnClickListener {

            startActivity(
                Intent(this, BuscarLibrosActivity::class.java)
            )

        }

        cardFavoritos.setOnClickListener {

            startActivity(
                Intent(this, FavoritosActivity::class.java)
            )

        }

        cardPerfil.setOnClickListener {

            startActivity(
                Intent(this, PerfilActivity::class.java)
            )

        }

        cardUsuarios.setOnClickListener {

            startActivity(
                Intent(this, UsuariosActivity::class.java)
            )

        }

        btnCerrarSesion.setOnClickListener {

            cerrarSesion()

        }

    }

    private fun cerrarSesion() {

        val shared = getSharedPreferences("SesionUsuario", MODE_PRIVATE)

        shared.edit().clear().apply()

        val intent = Intent(this, LoginActivity::class.java)

        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)

        finish()

    }

}