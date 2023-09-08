package com.assignment.shelfapp.data.model

import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName("region") var region: String? = null,
    @SerializedName("country") var country: String? = null,
)