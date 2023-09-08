package com.assignment.shelfapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import com.assignment.shelfapp.data.local.sharedPref.PrefConstants
import com.assignment.shelfapp.data.local.sharedPref.PrefManager
import com.assignment.shelfapp.data.model.BookModel
import com.assignment.shelfapp.databinding.ActivityHomeScreenBinding
import com.assignment.shelfapp.ui.baseActivity.BaseActivity
import com.assignment.shelfapp.ui.home.adapters.HomeScreenAdapter
import com.assignment.shelfapp.ui.user.LoginScreenActivity
import org.json.JSONArray
import java.io.IOException
import java.util.Locale.ROOT


class HomeScreenActivity : BaseActivity() {

    private lateinit var mBinding: ActivityHomeScreenBinding
    private lateinit var mHomeAdapter: HomeScreenAdapter
    private lateinit var mBookList: MutableList<BookModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()

        getBookList()

        mBinding.spShortBy.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                sortData(p0!!.selectedItem.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        mBinding.btnAscending.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                mBookList.sortBy { it.hits }
            } else {
                mBookList.sortBy { it.title }
            }
            setBookListAdapter()
        }

        mBinding.ivLogout.setOnClickListener {
            PrefManager.saveStringPrefValue(PrefConstants.IS_LOGIN, "false")
            startActivity(Intent(this, LoginScreenActivity::class.java))
            finish()
        }
    }


    private fun init() {
        mBookList = mutableListOf()
    }

    private fun getBookList() {
        mBookList.clear()

        val jsonString: String? = try {
            val `is` = assets.open("BookList.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            ""
        }
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            mBookList.add(
                BookModel(
                    jsonArray.getJSONObject(i).getString("id"),
                    jsonArray.getJSONObject(i).getString("image"),
                    jsonArray.getJSONObject(i).getInt("hits"),
                    jsonArray.getJSONObject(i).getString("alias"),
                    jsonArray.getJSONObject(i).getString("title"),
                    jsonArray.getJSONObject(i).getInt("lastChapterDate")
                )
            )
        }
    }


    private fun sortData(shortBy: String) {
        when (shortBy.lowercase(ROOT)) {
            "title" -> {
                mBookList.sortBy { it.title }
            }

            "hits" -> {
                mBookList.sortBy { it.hits }
            }

            "favs" -> {
                mBookList.sortBy { it.favs }
                mBookList.reverse()
            }
        }

        if (mBinding.btnAscending.isChecked) {
            mBookList.sortBy { it.hits }
        }

        setBookListAdapter()
    }


    private fun setBookListAdapter() {
        mHomeAdapter = HomeScreenAdapter(this, mBookList)
        mBinding.rvBooks.adapter = mHomeAdapter
    }

    fun updateDataInList(book: BookModel, fav: String, position: Int) {
        book.favs = fav
        mBookList.add(position, book)
        mHomeAdapter.notifyItemChanged(position)
    }


    override fun onDestroy() {
        super.onDestroy()
        mBookList.clear()
    }
}