package com.example.spoton.data.model


import com.google.gson.annotations.SerializedName

data class Crypto(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("timestamp")
    val timestamp: Long
)