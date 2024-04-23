package com.example.goodboy.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

abstract class BaseFragment : Fragment() {

    protected fun <State> Flow<State>.observe(observer: (State) -> Unit) {
        this.map(observer).launchIn(viewLifecycleOwner.lifecycleScope)
    }
}