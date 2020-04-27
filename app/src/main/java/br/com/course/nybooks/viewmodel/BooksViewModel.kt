package br.com.course.nybooks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.course.nybooks.R
import br.com.course.nybooks.data.BooksResult
import br.com.course.nybooks.data.model.Book
import br.com.course.nybooks.data.repository.BooksRepository

class BooksViewModel(private val repository: BooksRepository) : ViewModel() {

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getBooks() {
        repository.getBooks { result: BooksResult ->
            when (result) {
                is BooksResult.Sucess -> {
                    booksLiveData.value = result.books
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_BOOKS, null)
                }
                is BooksResult.ApiError -> {
                    if (result.statusCode == 401) {
                        viewFlipperLiveData.value =
                            Pair(VIEW_FLIPPER_ERRO, R.string.books_error_401)
                    } else {
                        viewFlipperLiveData.value =
                            Pair(VIEW_FLIPPER_ERRO, R.string.books_error_400_generic)
                    }
                }
                is BooksResult.ServerError -> {
                    viewFlipperLiveData.value =
                        Pair(BooksViewModel.VIEW_FLIPPER_ERRO, R.string.books_error_500_generic)
                }
            }
        }
    }

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERRO = 2
    }
}



