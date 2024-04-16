package com.example.goodboy.data.model

import com.google.gson.annotations.SerializedName

data class DogWeight(

    @field:SerializedName("imperial")
    val imperial: String?,

    @field:SerializedName("metric")
    val metric: String?
)
