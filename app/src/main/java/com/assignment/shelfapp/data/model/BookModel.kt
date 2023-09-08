package com.assignment.shelfapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class BookModel(
    @SerializedName("id") var id: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("hits") var hits: Int? = null,
    @SerializedName("alias") var alias: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("lastChapterDate") var lastChapterDate: Int? = null,
    @SerializedName("favs") var favs: String = "",
): Serializable
