package com.assignment.shelfapp.ui.bookDetails

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.assignment.shelfapp.R
import com.assignment.shelfapp.data.model.BookModel
import com.assignment.shelfapp.databinding.ActivityBookDetailsBinding
import com.assignment.shelfapp.utils.Constants
import com.bumptech.glide.Glide

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityBookDetailsBinding
    private lateinit var book: BookModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        if (intent.hasExtra("data")) {
            val bundle = intent.getBundleExtra("data")

            book = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle!!.getSerializable(Constants.BOOK_DETAILS, BookModel::class.java)!!
            } else {
                bundle!!.getSerializable(Constants.BOOK_DETAILS) as BookModel
            }

            setBookDetails()
        }

    }

    private fun setBookDetails() {
        book.image?.let {
            Glide.with(this).load(Uri.parse(it)).into(mBinding.ivBook)
        }

        book.title?.let {
            mBinding.tvBookTitle.text = it
        }

        book.hits?.let {
            mBinding.tvHits.text = it.toString()
        }

        if (book.favs == "Y") {
            mBinding.ivFav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.baseline_favorite_24
                )
            )
        } else {
            mBinding.ivFav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.baseline_favorite_border_24
                )
            )
        }
    }
}