package com.assignment.shelfapp.utils

import android.content.Context
import android.util.Log
import com.assignment.shelfapp.data.model.CountryModel
import com.google.gson.Gson
import org.json.JSONObject
import java.io.IOException


class AppData {

    companion object{
         fun getCountryList(context: Context) : MutableList<String> {
            val jsonString: String? = try {
                val `is` = context.assets.open("CountryList.json")
                val size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                String(buffer)
            } catch (ex: IOException) {
                ex.printStackTrace()
                ""
            }

            val data = JSONObject(jsonString).getJSONObject("data")

            val countryData  = mutableMapOf<String, Any>().apply {
                data.keys().forEach { put(it, data[it]) }
            }

            val countryList = mutableListOf<String>()
            countryData.forEach {
                val gson = Gson()
                val countryModel  = gson.fromJson(it.value.toString(), CountryModel::class.java)

                countryModel.country?.let { it1 -> countryList.add(it1) }
            }

             countryList.add(0, "Select Country")

            return countryList
        }
    }

}