package com.example.goodboy.ui.vote.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.goodboy.R
import com.example.goodboy.common.BaseFragment
import com.example.goodboy.databinding.FragmentVoteBinding
import com.example.goodboy.ui.vote.viewmodel.VoteViewModel
import com.example.goodboy.ui.vote.viewstate.VoteViewState

class VoteFragment : BaseFragment() {

    private lateinit var binding: FragmentVoteBinding

    private val viewModel: VoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVoteBinding.inflate(inflater, container, false)

        viewModel.viewState.observe(::handleViewState)
        viewModel.actionFlow.observe(::handleAction)

        binding.voteButton.setOnClickListener { viewModel.clickToVote(binding.ratingBar.rating) }
        binding.myVotesButton.setOnClickListener { viewModel.clickMyVote() }

        return binding.root
    }

    private fun handleViewState(viewState: VoteViewState) {
        when (viewState) {

            VoteViewState.Loading -> {
                binding.ratingBar.rating = EMPTY_RATING
                binding.progressBar.isVisible = true
                binding.voteButton.isEnabled = false
                binding.ratingBar.isEnabled = false

            }

            is VoteViewState.LoadingSuccess -> {
                binding.ratingBar.rating = EMPTY_RATING
                binding.imageView.load(viewState.image?.url) {
                    crossfade(true)
                    error(R.drawable.image_plug)
                    listener(
                        onSuccess = { _, _ ->
                            binding.progressBar.isVisible = false
                            binding.voteButton.isEnabled = true
                            binding.ratingBar.isEnabled = true
                        },
                        onError = { _, _ ->
                            binding.progressBar.isVisible = false
                            binding.voteButton.isEnabled = false
                            binding.ratingBar.isEnabled = false
                        },
                        onStart = {
                            binding.progressBar.isVisible = true
                            binding.voteButton.isEnabled = false
                            binding.ratingBar.isEnabled = false
                        }
                    )
                }
            }

            else -> {
                binding.progressBar.isVisible = true
                binding.voteButton.isEnabled = false
                binding.ratingBar.isEnabled = false
            }
        }
    }

    private fun handleAction(action: VoteViewModel.Action) {
        when (action) {
            is VoteViewModel.Action.ShowMessage -> {
                Toast.makeText(context, action.voteMessage, Toast.LENGTH_SHORT).show()
            }

            is VoteViewModel.Action.OpenMyVote -> {
                findNavController().navigate(R.id.action_voteFragment_to_myVoteFragment)
            }
        }
    }

    companion object {

        const val EMPTY_RATING = 0F
    }
}