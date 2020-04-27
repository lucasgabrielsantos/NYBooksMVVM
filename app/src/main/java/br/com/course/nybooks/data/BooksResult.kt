package br.com.course.nybooks.data

import br.com.course.nybooks.data.model.Book

sealed class BooksResult {
    class Sucess(val books : List<Book>) : BooksResult()
    class ApiError(val statusCode: Int): BooksResult()
    object ServerError : BooksResult()
}