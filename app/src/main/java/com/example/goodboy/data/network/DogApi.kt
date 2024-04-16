package com.example.goodboy.data.network

import com.example.goodboy.data.model.Dog
import com.example.goodboy.data.model.Image
import com.example.goodboy.data.model.Vote
import com.example.goodboy.data.model.VoteMessage
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DogApi {

    @GET("breeds")
    suspend fun getDogBreeds(): List<Dog>

    @GET("breeds/search")
    suspend fun getDogByBreed(@Query("q") breed: String): List<Dog>

    @GET("images/search")
    suspend fun getRandomDogImage(): Image

    @GET("images/{imageId}")
    suspend fun getDogImageById(@Path("imageId") imageId: String): Image

    @POST("votes")
    suspend fun postDogVote(@Body vote: Vote): VoteMessage

    @GET("votes")
    suspend fun getMyDogVotes(): List<Vote>

    @GET("votes/{voteId}")
    suspend fun getMyDogVoteById(@Path("voteId") voteId: Int): Vote

    @DELETE("votes/{voteId}")
    suspend fun deleteDogVote(@Path("voteId") voteId: Int): VoteMessage

}