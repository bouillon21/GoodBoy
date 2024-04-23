package com.example.goodboy.ui.breed.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.goodboy.R
import com.example.goodboy.common.BaseFragment
import com.example.goodboy.data.model.Dog
import com.example.goodboy.databinding.FragmentDogBreedsBinding
import com.example.goodboy.ui.animator.DogBreedAnimator
import com.example.goodboy.ui.breed.adapter.PreviewDogAdapter
import com.example.goodboy.ui.breed.viewmodel.DogBreedsViewModel
import com.example.goodboy.ui.breed.viewstate.DogBreedsViewState

class DogBreedsFragment : BaseFragment() {

    private lateinit var binding: FragmentDogBreedsBinding

    private lateinit var adapter: PreviewDogAdapter

    private lateinit var layoutManager: LayoutManager

    private val homeViewModel: DogBreedsViewModel by viewModels<DogBreedsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogBreedsBinding.inflate(inflater, container, false)
        layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = PreviewDogAdapter(homeViewModel::clickOnBreeds)

        binding.dogRv.adapter = adapter
        binding.dogRv.layoutManager = layoutManager
        binding.dogRv.itemAnimator = DogBreedAnimator()

        binding.refresh.setOnRefreshListener {
            homeViewModel.updateBreedsList()
            binding.refresh.isRefreshing = false
        }

        homeViewModel.actionFlow.observe(::handleAction)
        homeViewModel.dogs.observe(::handleViewState)

        return binding.root
    }

    private fun handleViewState(viewState: DogBreedsViewState) {
        val dogBreeds = viewState.dogBreeds as ArrayList<Dog>?
        adapter.dogs = dogBreeds ?: arrayListOf()
        binding.dogBreedProgressBar.isVisible = adapter.itemCount == 0
    }

    private fun handleAction(action: DogBreedsViewModel.Action) {
        when (action) {
            is DogBreedsViewModel.Action.OpenDetail -> {
                val bundle = DogBreedsDetailFragment.getBundle(action.breed)
                findNavController().navigate(
                    R.id.action_dogBreedsFragment_to_dogBreedsDetailFragment,
                    bundle
                )
            }
        }
    }
}