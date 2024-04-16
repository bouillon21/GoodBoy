package com.example.goodboy.ui.breed.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import coil.load
import com.example.goodboy.R
import com.example.goodboy.databinding.FragmentDogBreedsDetailBinding
import com.example.goodboy.ui.breed.viewmodel.DogBreedsDetailViewModel
import com.example.goodboy.ui.breed.viewmodel.DogBreedsViewModel
import com.example.goodboy.ui.breed.viewstate.BaseViewState
import com.example.goodboy.ui.breed.viewstate.DogBreedDetailViewState

class DogBreedsDetailFragment : BaseFragment<DogBreedsViewModel.Action>() {

    private lateinit var binding: FragmentDogBreedsDetailBinding
    private lateinit var progress: FrameLayout

    private val viewModel: DogBreedsDetailViewModel by lazy {
        DogBreedsDetailViewModel(::getBreed)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDogBreedsDetailBinding.inflate(inflater, container, false)
        progress = binding.progressOverlay.progress

        progress.visibility = View.VISIBLE
        viewModel.dog.observeViewState(::handleViewState)

        return binding.root
    }

    private fun handleViewState(viewState: BaseViewState) {
        if (viewState is DogBreedDetailViewState) {
            val dog = viewState.dog
            binding.apply {
                dog?.let {
                    breed.text = dog.breed
                    dogImage.load(dog.image?.url ?: R.drawable.image_plug)
                    idText.text = dog.breedId
                    age.text = dog.age
                    weight.text = dog.weight?.metric
                    height.text = dog.height?.metric
                    temperament.text = dog.temperament
                    origin.text = dog.origin
                    progress.visibility = View.GONE
                }
            }
        }
    }

    private fun getBreed(): String {
        return arguments?.getString(BREED_KEY) ?: EMPTY_VALUE
    }

    companion object {

        private const val EMPTY_VALUE = ""
        private const val BREED_KEY = "breed_key"

        fun getBundle(breed: String): Bundle {
            val bundle = Bundle()
            bundle.putString(BREED_KEY, breed)
            return bundle
        }
    }

}
