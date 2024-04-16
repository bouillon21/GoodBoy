package com.example.goodboy.data.network.repository

import android.util.Log
import com.example.goodboy.data.model.Dog
import com.example.goodboy.data.model.Image
import com.example.goodboy.data.model.Vote
import com.example.goodboy.data.model.VoteMessage
import com.example.goodboy.data.network.DogService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object DogBreedRepository {

    private val dogApi = DogService.getInstance().getAPI()

    fun getBreedsList(): Flow<List<Dog>> {
        return flow { emit(dogApi.getDogBreeds().shuffled()) }
            .flowOn(Dispatchers.IO)
            .catch { onMessageFail(it) }
    }

    fun getDogByBreed(breed: String): Flow<List<Dog>> {
        return flow { emit(dogApi.getDogByBreed(breed)) }
            .flowOn(Dispatchers.IO)
            .catch { onMessageFail(it) }
    }

    fun getRandomDogImage(): Flow<Image> {
        return flow { emit(dogApi.getRandomDogImage()) }
            .flowOn(Dispatchers.IO)
            .catch { onMessageFail(it) }
    }

    fun getDogImageById(imageId: String): Flow<Image> {
        return flow { emit(dogApi.getDogImageById(imageId)) }
            .flowOn(Dispatchers.IO)
            .catch { onMessageFail(it) }
    }

    fun postDogVote(vote: Vote): Flow<VoteMessage> {
        return flow { emit(dogApi.postDogVote(vote)) }
            .flowOn(Dispatchers.IO)
            .catch { onMessageFail(it) }
    }

    fun deleteDogVote(voteId: Int): Flow<VoteMessage> {
        return flow { emit(dogApi.deleteDogVote(voteId)) }
            .flowOn(Dispatchers.IO)
            .catch { onMessageFail(it) }
    }

    fun getMyDogVotes(): Flow<List<Vote>> {
        return flow { emit(dogApi.getMyDogVotes()) }
            .flowOn(Dispatchers.IO)
            .catch { onMessageFail(it) }
    }

    fun getMyDogVoteById(voteId: Int): Flow<Vote> {
        return flow { emit(dogApi.getMyDogVoteById(voteId)) }
            .flowOn(Dispatchers.IO)
            .catch { onMessageFail(it) }
    }

    private fun onMessageFail(msg: Throwable) {
        Log.e("NetworkDog", "Dog list download fail: ${msg.message}")
    }
}