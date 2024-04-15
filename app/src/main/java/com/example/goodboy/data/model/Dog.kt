package com.example.goodboy.data.model

import com.google.gson.annotations.SerializedName

data class Dog(

    @field:SerializedName("id")
    val breedId: String? = null,

    @field:SerializedName("name")
    val breed: String? = null,

    @field:SerializedName("image")
    val image: Image? = null,

    @field:SerializedName("weight")
    val weight: DogWeight? = null,

    @field:SerializedName("height")
    val height: DogHeight? = null,

    @field:SerializedName("life_span")
    val age: String? = null,

    @field:SerializedName("temperament")
    val temperament: String? = null,

    @field:SerializedName("origin")
    val origin: String? = null,

)
