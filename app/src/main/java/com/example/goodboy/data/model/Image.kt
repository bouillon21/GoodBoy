package com.example.goodboy.data.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

data class Image(

    @field:SerializedName("id")
    val id:String?,

    @field:SerializedName("url")
    val url:String?
)
