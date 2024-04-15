package com.example.goodboy.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogService {

    private val retrofit: Retrofit

    init {
        val httpClient =
            OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.addHeader("x-api-key", DogApiPref.API_KEY)
                chain.proceed(requestBuilder.build())
            }).build()
        retrofit = Retrofit.Builder().baseUrl(DogApiPref.BASE_URL).client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getAPI(): DogApi = retrofit.create(DogApi::class.java)

    companion object {

        private var instance: DogService? = null
        fun getInstance(): DogService = instance ?: DogService()
    }
}