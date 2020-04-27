package br.com.course.nybooks.data.repository

import br.com.course.nybooks.R
import br.com.course.nybooks.data.ApiServiceRetrofit
import br.com.course.nybooks.data.BooksResult
import br.com.course.nybooks.data.model.Book
import br.com.course.nybooks.data.response.BookBodyResponse
import br.com.course.nybooks.viewmodel.BooksViewModel
import retrofit2.Call
import retrofit2.Response

class BooksRepositoryApi : BooksRepository {

    override fun getBooks(booksResultsCallback: (result: BooksResult) -> Unit) {
        ApiServiceRetrofit.service.getBooks().enqueue(object :
            retrofit2.Callback<BookBodyResponse> {
            override fun onResponse(
                call: Call<BookBodyResponse>, response: Response<BookBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let { bookBodyResponse ->
                            for (result in bookBodyResponse.bookResults) {
                                val book = result.bookDetailsResponse[0].getBookModel()
                                books.add(book)
                            }
                        }
                        booksResultsCallback(BooksResult.Sucess(books))
                    }
                    else -> booksResultsCallback(BooksResult.ApiError(response.code()))
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
               booksResultsCallback(BooksResult.ServerError)
            }
        })
    }
}