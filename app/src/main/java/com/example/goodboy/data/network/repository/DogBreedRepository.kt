package com.example.goodboy.data.network.repository

import android.util.Log
import com.example.goodboy.data.model.Dog
import com.example.goodboy.data.model.Image
import com.example.goodboy.data.model.Vote
import com.example.goodboy.data.model.VoteMessage
import com.example.goodboy.data.network.DogService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DogBreedRepository {

    private val dogApi = DogService.getInstance().getAPI()

    suspend fun getBreedsList(): List<Dog> {
        return withContext(Dispatchers.IO) { dogApi.getDogBreeds().shuffled() }
    }

    suspend fun getDogByBreed(breed: String): List<Dog> {
        return withContext(Dispatchers.IO) { dogApi.getDogByBreed(breed) }
    }

    suspend fun getRandomDogImage(): Image? {
        return withContext(Dispatchers.IO) { dogApi.getRandomDogImage().firstOrNull() }
    }

    suspend fun getDogImageById(imageId: String): Image {
        return withContext(Dispatchers.IO) { dogApi.getDogImageById(imageId) }
    }

    suspend fun postDogVote(vote: Vote): VoteMessage {
        return withContext(Dispatchers.IO) { dogApi.postDogVote(vote) }
    }

    suspend fun deleteDogVote(voteId: Int): VoteMessage {
        return withContext(Dispatchers.IO) { dogApi.deleteDogVote(voteId) }
    }

    suspend fun getMyDogVotes(): List<Vote> {
        return withContext(Dispatchers.IO) { dogApi.getMyDogVotes() }
    }

    suspend fun getMyDogVoteById(voteId: Int): Vote {
        return withContext(Dispatchers.IO) { dogApi.getMyDogVoteById(voteId) }
    }

    private fun onMessageFail(msg: Throwable) {
        Log.e("NetworkDog", "Dog list download fail: ${msg.message}")
    }
}