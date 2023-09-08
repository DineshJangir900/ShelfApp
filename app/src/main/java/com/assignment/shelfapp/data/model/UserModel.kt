package com.assignment.shelfapp.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name : String? = null,
    @SerializedName("password") var password : String? = null,
    @SerializedName("country") var country : String? = null,
)
