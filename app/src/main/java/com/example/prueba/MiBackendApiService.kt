package com.example.prueba

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// --- ESTRUCTURAS DE DATOS ---
data class UserRequest(val usuario: String, val contrasena: String, val correo: String)
data class LoginResponse(val existe: Boolean)
data class RegistroResponse(val exito: Boolean, val mensaje: String)
data class FavoritoRequest(val usuario: String, val libroId: String, val titulo: String)
data class FavoritoResponse(val exito: Boolean, val mensaje: String)

// --- INTERFAZ ---
interface MiBackendApiService {
    @POST("api/login")
    suspend fun login(@Body request: UserRequest): Response<LoginResponse>

    @POST("api/usuarios/registrar")
    suspend fun registrarUsuario(@Body request: UserRequest): Response<RegistroResponse>

    @POST("api/favoritos/agregar")
    suspend fun agregarFavorito(@Body request: FavoritoRequest): Response<FavoritoResponse>

    @GET("api/favoritos/{usuario}")
    suspend fun obtenerFavoritos(@Path("usuario") usuario: String): Response<List<FavoritoRequest>>
}