package com.example.goodboy.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Action> : ViewModel() {

    private val actionChannel: Channel<Action> = Channel()
    val actionFlow: Flow<Action> = actionChannel.receiveAsFlow()

    fun Action.send() {
        viewModelScope.launch {
            actionChannel.send(this@send)
        }
    }
}