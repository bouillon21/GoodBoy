package com.example.goodboy.ui.vote.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.goodboy.common.BaseViewModel
import com.example.goodboy.data.network.repository.DogBreedRepository
import com.example.goodboy.ui.vote.viewstate.MyVoteViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MyVoteViewModel : BaseViewModel<MyVoteViewModel.Action>() {

    private val repository = DogBreedRepository

    private val mutableViewState = MutableStateFlow<MyVoteViewState>(MyVoteViewState.Initial)
    val viewState = mutableViewState.asSharedFlow()

    init {
        updateMyVoteList()
    }

    private fun updateMyVoteList() {
        viewModelScope.launch {
            mutableViewState.emit(MyVoteViewState.Loading)
            repository.getMyDogVotes().let { mutableViewState.emit(MyVoteViewState.LoadingSuccess(it)) }
        }
    }

    sealed class Action {

    }
}