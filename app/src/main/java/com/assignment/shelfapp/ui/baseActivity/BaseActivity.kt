package com.assignment.shelfapp.ui.baseActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.assignment.shelfapp.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_NoActionBar)
    }
}