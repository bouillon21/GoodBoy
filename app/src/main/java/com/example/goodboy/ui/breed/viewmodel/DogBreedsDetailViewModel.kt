package com.example.goodboy.ui.breed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodboy.data.network.repository.DogBreedRepository
import com.example.goodboy.ui.breed.viewstate.DogBreedDetailViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DogBreedsDetailViewModel(getBreed: () -> String) : ViewModel() {

    private val repository = DogBreedRepository

    private val mutableDog = MutableStateFlow(DogBreedDetailViewState(null))
    val dog: SharedFlow<DogBreedDetailViewState> = mutableDog.asStateFlow()

    init {
        updateDogByBreed(getBreed())
    }

    private fun updateDogByBreed(breed: String) {
        viewModelScope.launch {
            repository.getDogByBreed(breed)
                .let { mutableDog.emit(DogBreedDetailViewState(it.firstOrNull())) }
        }
    }

}