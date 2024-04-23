package com.example.goodboy.ui.breed.viewstate

import com.example.goodboy.data.model.Dog

data class DogBreedsViewState(
    val dogBreeds: List<Dog>?,
    var isRefreshing: Boolean = true
)
