package br.com.course.nybooks.data

import br.com.course.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NYTServices {
    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "7b4c58b5-f64c-4fcf-abc2-be834edae23b",
        @Query("list")list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}