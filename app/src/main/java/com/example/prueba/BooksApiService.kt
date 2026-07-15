package com.example.prueba

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    fun searchBooks(
        @Query("q") query: String,
        @Query("key") apiKey: String = "AIzaSyAbHSkSMMHTnLve9WACBEPF-DeIqlgIsA0"
    ): Call<BookResponse>
}