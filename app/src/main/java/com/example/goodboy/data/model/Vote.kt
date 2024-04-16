package com.example.goodboy.data.model

import com.google.gson.annotations.SerializedName

data class Vote(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("sub_id")
    val subId: String? = null,

    @field:SerializedName("image_id")
    val imageId: String? = null,

    @field:SerializedName("value")
    val value: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null
)