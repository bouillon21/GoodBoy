package com.example.goodboy.ui.breed.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.goodboy.ui.breed.viewmodel.BaseViewModel
import com.example.goodboy.ui.breed.viewmodel.DogBreedsViewModel
import com.example.goodboy.ui.breed.viewstate.BaseViewState
import com.example.goodboy.ui.breed.viewstate.DogBreedsViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseFragment<Action> : Fragment() {

    protected fun Flow<Action>.observe(observer: (Action) -> Unit) {
        this.map(observer).launchIn(viewLifecycleOwner.lifecycleScope)
    }

    protected fun Flow<BaseViewState>.observeViewState(observer: (BaseViewState) -> Unit) {
        this.map(observer).launchIn(viewLifecycleOwner.lifecycleScope)
    }

}