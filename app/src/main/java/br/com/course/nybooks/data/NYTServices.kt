package br.com.course.nybooks.data

import br.com.course.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NYTServices {
    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "58XXEnecx2CnMrI66t93lnQYiUEFUUq2",
        @Query("list")list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}