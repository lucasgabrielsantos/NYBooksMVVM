package br.com.course.nybooks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.course.nybooks.data.model.Book
import br.com.course.nybooks.data.repository.BooksRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(val repository: BooksRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel::class.java)){
            return BooksViewModel(repository) as T
        }
        throw IllegalArgumentException("Classe View Model não encontrada")
    }
}