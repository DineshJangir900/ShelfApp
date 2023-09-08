package com.assignment.shelfapp

import android.app.Application

class ShelfApplication : Application() {

    companion object{
        var mInstance : ShelfApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

}