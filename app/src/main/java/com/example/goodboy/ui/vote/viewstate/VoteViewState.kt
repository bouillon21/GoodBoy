package com.example.goodboy.ui.vote.viewstate

import com.example.goodboy.data.model.Image

sealed class VoteViewState {

    data object Initial : VoteViewState()

    data object Loading : VoteViewState()

    data object LoadingError : VoteViewState()

    data class LoadingSuccess(
        val image: Image?
    ) : VoteViewState()

    data object Submitting : VoteViewState()
}
