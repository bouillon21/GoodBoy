package com.example.goodboy.ui.vote.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.goodboy.common.BaseViewModel
import com.example.goodboy.data.model.Image
import com.example.goodboy.data.model.Vote
import com.example.goodboy.data.network.DogApiPref
import com.example.goodboy.data.network.repository.DogBreedRepository
import com.example.goodboy.ui.vote.viewstate.VoteViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class VoteViewModel : BaseViewModel<VoteViewModel.Action>() {

    private val repository = DogBreedRepository

    private val imageFlow = MutableStateFlow(Image())

    private val mutableViewState = MutableStateFlow<VoteViewState>(VoteViewState.Initial)
    val viewState = mutableViewState.asSharedFlow()

    init {
        getRandomImage()
        imageFlow.map { mutableViewState.emit(VoteViewState.LoadingSuccess(it)) }
            .launchIn(viewModelScope)
    }

    private fun getRandomImage() {
        viewModelScope.launch {
            mutableViewState.emit(VoteViewState.Loading)
            repository.getRandomDogImage()?.let { imageFlow.emit(it) }
        }
    }

    fun clickMyVote() {
        Action.OpenMyVote().send()
    }

    fun clickToVote(rating: Float) {
        viewModelScope.launch {
            val voteAnswer = repository.postDogVote(
                Vote(
                    subId = DogApiPref.USER_NAME,
                    value = getConvertRating(rating),
                    imageId = imageFlow.value.id
                )
            )
            getRandomImage()
            Action.ShowMessage(voteAnswer.message).send()
        }
    }

    private fun getConvertRating(rating: Float): Int = (rating * RATING_RATIO).toInt()

    sealed class Action {

        class OpenMyVote : Action()

        class ShowMessage(val voteMessage: String?) : Action()
    }

    companion object {

        const val RATING_RATIO = 2F
    }

}