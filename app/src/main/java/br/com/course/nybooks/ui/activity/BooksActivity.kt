package br.com.course.nybooks.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.course.nybooks.R
import br.com.course.nybooks.base.BaseActivity
import br.com.course.nybooks.ui.adapter.BooksAdapter
import br.com.course.nybooks.viewmodel.BooksViewModel
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.include_toolbar.*

class BooksActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar(toolbar, R.string.book_title_label)

        val viewModel: BooksViewModel = ViewModelProviders.of(this).get(
            BooksViewModel::class.java)

        viewModel.booksLiveData.observe(this, Observer {
            it?.let { books ->
                with(recyclerview) {
                    layoutManager =
                        LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter =
                        BooksAdapter(books) { book ->
                            val intent = DetailsActivity.getStartIntent(
                                this@BooksActivity,
                                book.title,
                                book.description
                            )
                            this@BooksActivity.startActivity(intent)
                        }
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                viewFlipperBooks.displayedChild = viewFlipper.first
                viewFlipper.second?.let { errorMessageResID ->
                    textError.text = getString(errorMessageResID)
                }
            }
        })
        viewModel.getBooks()
    }


}
