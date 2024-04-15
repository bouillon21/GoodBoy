package com.example.goodboy.data.network

import com.example.goodboy.data.model.Dog
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface DogApi {

    @GET("breeds")
    suspend fun getDogBreeds(): List<Dog>

    @GET("breeds/search")
    suspend fun getDogByBreed(@Query("q")breed: String): List<Dog>

}