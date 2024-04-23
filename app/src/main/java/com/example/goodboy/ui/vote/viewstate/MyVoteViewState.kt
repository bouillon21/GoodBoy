package com.example.goodboy.ui.vote.viewstate

import com.example.goodboy.data.model.Vote

sealed class MyVoteViewState {

    data object Initial : MyVoteViewState()

    data object Loading : MyVoteViewState()

    data object LoadingError : MyVoteViewState()

    data class LoadingSuccess(
        val myVotes: List<Vote>?
    ) : MyVoteViewState()

}
