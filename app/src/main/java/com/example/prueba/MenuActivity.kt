package com.example.prueba

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.button.MaterialButton

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // 1. Buscamos las vistas
        val cardBuscar = findViewById<CardView>(R.id.cardBuscar)
        val cardFavoritos = findViewById<CardView>(R.id.cardFavoritos)
        val cardPerfil = findViewById<CardView>(R.id.cardPerfil)
        val cardUsuarios = findViewById<CardView>(R.id.cardUsuarios)
        val btnCerrar = findViewById<MaterialButton>(R.id.btnCerrarSesion)

        // 2. Definimos las acciones al hacer clic
        cardBuscar.setOnClickListener {
            startActivity(Intent(this, BuscarLibrosActivity::class.java))
        }

        cardFavoritos.setOnClickListener {
            startActivity(Intent(this, FavoritosActivity::class.java))
        }

        cardPerfil.setOnClickListener {
            startActivity(Intent(this, PerfilActivity::class.java))
        }

        cardUsuarios.setOnClickListener {
            startActivity(Intent(this, UsuariosActivity::class.java))
        }

        btnCerrar.setOnClickListener {
            finish() // Cierra la app o regresa al login
        }
    }
}