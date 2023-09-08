package com.assignment.shelfapp.data.local.sharedPref

import android.content.SharedPreferences
import com.assignment.shelfapp.ShelfApplication
import com.assignment.shelfapp.utils.Util

object PrefManager {

    private val pref: SharedPreferences =
        Util.getSharedPreferences(ShelfApplication.mInstance!!)

    private val editor = pref.edit()

    fun getPref(): SharedPreferences {
        return pref
    }

    fun getEditor(): SharedPreferences.Editor? {
        return editor
    }


    fun saveLongPrefValue(key: String?, value: Long) {
        editor.putLong(key, value).apply()
    }

    fun getLongPrefValue(key: String?, defaultValue: Long): Long {
        return pref.getLong(key, defaultValue)
    }

    fun saveIntPrefValue(key: String?, value: Int) {
        editor.putInt(key, value).apply()
    }

    fun getIntPrefValue(key: String?, defaultValue: Int): Int {
        return pref.getInt(key, defaultValue)
    }

    fun saveBooleanPrefValue(key: String?, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun getBooleanPrefValue(key: String?, defaultValue: Boolean): Boolean {
        return pref.getBoolean(key, defaultValue)
    }

    fun saveStringPrefValue(key: String?, value: String?) {
        editor.putString(key, value).apply()
    }

    fun getStringPrefValue(key: String?, defaultValue: String?): String? {
        return pref.getString(key, defaultValue)
    }

    fun saveFloatPrefValue(key: String?, value: Float) {
        editor.putFloat(key, value).apply()
    }


    fun getFloatPrefValue(key: String?, defaultValue: Float): Float {
        return pref.getFloat(key, defaultValue)
    }

    fun getStringList(key: String?): String? {
        return pref.getString(key, "")
    }

    fun saveStringList(key: String?, json: String?) {
        editor.putString(key, json)
    }

    fun removeStringPrefValue(key: String?) {
        editor.remove(key).apply()
    }
}