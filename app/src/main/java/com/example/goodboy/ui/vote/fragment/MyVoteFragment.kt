package com.example.goodboy.ui.vote.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.goodboy.common.BaseFragment
import com.example.goodboy.data.model.Vote
import com.example.goodboy.databinding.FragmentMyVoteBinding
import com.example.goodboy.ui.vote.adapter.PreviewMyVoteAdapter
import com.example.goodboy.ui.vote.viewmodel.MyVoteViewModel
import com.example.goodboy.ui.vote.viewstate.MyVoteViewState

class MyVoteFragment : BaseFragment() {

    private lateinit var binding: FragmentMyVoteBinding

    private lateinit var adapter: PreviewMyVoteAdapter

    private val viewModel: MyVoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyVoteBinding.inflate(inflater, container, false)
        val layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = PreviewMyVoteAdapter()

        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = layoutManager

        viewModel.viewState.observe(::handleViewState)

        return binding.root
    }

    private fun handleViewState(viewState: MyVoteViewState) {
        when (viewState) {
            is MyVoteViewState.LoadingSuccess -> {
                val votes = viewState.myVotes as ArrayList<Vote>?
                adapter.votes = votes ?: arrayListOf()
                binding.progressBar.isVisible = false
            }

            else -> {
                binding.progressBar.isVisible = true
            }

        }
    }
}