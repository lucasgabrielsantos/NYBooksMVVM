package br.com.course.nybooks.data.repository

import br.com.course.nybooks.data.BooksResult

interface BooksRepository {

    fun getBooks(booksResultsCallback: (result: BooksResult) -> Unit)
}