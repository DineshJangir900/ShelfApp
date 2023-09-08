package com.assignment.shelfapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.assignment.shelfapp.R

object Util {

    fun getSharedPreferences(context : Context) : SharedPreferences {
        return context.applicationContext.getSharedPreferences(
            context.getString(R.string.PREFERENCE_FILE_KEY), Context.MODE_PRIVATE
        )
    }

}