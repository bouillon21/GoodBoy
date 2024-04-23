package com.example.goodboy.data.model

import com.google.gson.annotations.SerializedName

data class Image(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)
