package com.example.prueba

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LibroAdapter(
    private val listaLibros: List<Libro>,
    private val onFavoritoClick: (Libro) -> Unit = {}
) : RecyclerView.Adapter<LibroAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.tvTitulo)
        val autor: TextView = view.findViewById(R.id.tvAutor)
        val portada: ImageView = view.findViewById(R.id.imgPortada)
        val btnFavorito: ImageView = view.findViewById(R.id.btnFavorito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_libro, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val libro = listaLibros[position]
        holder.titulo.text = libro.titulo
        holder.autor.text = libro.autor

        Glide.with(holder.portada.context)
            .load(libro.portadaUrl.replace("http:", "https:"))
            .into(holder.portada)

        if (FavoritosManager.listaFavoritos.contains(libro)) {
            holder.btnFavorito.setImageResource(android.R.drawable.btn_star_big_on)
        } else {
            holder.btnFavorito.setImageResource(android.R.drawable.btn_star_big_off)
        }

        holder.btnFavorito.setOnClickListener {
            FavoritosManager.agregar(libro)
            holder.btnFavorito.setImageResource(android.R.drawable.btn_star_big_on)
            onFavoritoClick(libro)
        }
    }

    override fun getItemCount() = listaLibros.size
}