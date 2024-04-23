package com.example.goodboy.ui.breed.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.goodboy.common.BaseViewModel
import com.example.goodboy.data.network.repository.DogBreedRepository
import com.example.goodboy.ui.breed.viewstate.DogBreedsViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DogBreedsViewModel : BaseViewModel<DogBreedsViewModel.Action>() {

    private val repository = DogBreedRepository

    private val mutableDogs = MutableStateFlow(DogBreedsViewState(null))
    val dogs: SharedFlow<DogBreedsViewState> = mutableDogs.asStateFlow()

    init {
        updateBreedsList()
    }

    fun updateBreedsList() {
        viewModelScope.launch {
            repository.getBreedsList()
                .let { mutableDogs.emit(DogBreedsViewState(it, false)) }
        }
    }

    fun clickOnBreeds(bread: String) {
        Action.OpenDetail(bread).send()
    }

    sealed class Action {
        class OpenDetail(val breed: String) : Action()
    }
}