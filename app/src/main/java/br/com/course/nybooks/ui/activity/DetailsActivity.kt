package br.com.course.nybooks.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.course.nybooks.R
import br.com.course.nybooks.base.BaseActivity
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.include_toolbar.*

class DetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setupToolbar(toolbar, R.string.book_details_label, true)

        title02.text = intent.getStringExtra(EXTRA_TITLE)
        description02.text = intent.getStringExtra(EXTRA_DESCRIPTION)

    }

    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
        fun getStartIntent(context: Context, title: String, description: String): Intent {

            return Intent(context, DetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_DESCRIPTION, description)

            }
        }
    }
}
