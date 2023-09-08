package com.assignment.shelfapp.ui.home.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.assignment.shelfapp.R
import com.assignment.shelfapp.data.model.BookModel
import com.assignment.shelfapp.databinding.HomeScreenAdpaterItemLayBinding
import com.assignment.shelfapp.ui.bookDetails.BookDetailsActivity
import com.assignment.shelfapp.ui.home.HomeScreenActivity
import com.assignment.shelfapp.utils.Constants
import com.bumptech.glide.Glide

class HomeScreenAdapter(
    private val context: Context,
    private val bookList: MutableList<BookModel>
) : RecyclerView.Adapter<HomeScreenAdapter.HomeScreenViewHolder>() {

    class HomeScreenViewHolder(val binding: HomeScreenAdpaterItemLayBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeScreenViewHolder {
        val binding =
            HomeScreenAdpaterItemLayBinding.inflate(LayoutInflater.from(context), parent, false)
        return HomeScreenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeScreenViewHolder, position: Int) {
        val book = bookList[position]

        if (book.favs == "Y") {
            holder.binding.ivFav.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.baseline_favorite_24
                )
            )
        } else {
            holder.binding.ivFav.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.baseline_favorite_border_24
                )
            )
        }

        book.image?.let {
            Glide.with(context).load(Uri.parse(it)).into(holder.binding.ivBook)
        }

        book.title?.let {
            holder.binding.tvBookTitle.text = it
        }

        book.hits?.let {
            holder.binding.tvHits.text = it.toString()
        }

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(Constants.BOOK_DETAILS, book)
            val bookDetailIntent = Intent(context, BookDetailsActivity::class.java)
            bookDetailIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            bookDetailIntent.putExtra("data", bundle)
            context.startActivity(bookDetailIntent)
        }

        holder.binding.ivFav.setOnClickListener {
            if (book.favs == "Y") {
                (context as HomeScreenActivity).updateDataInList(book, "", position)
            } else {
                (context as HomeScreenActivity).updateDataInList(book, "Y", position)
            }
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}
