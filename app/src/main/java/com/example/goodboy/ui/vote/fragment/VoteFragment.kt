package com.example.goodboy.ui.vote.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.goodboy.databinding.FragmentVoteBinding
import com.example.goodboy.ui.vote.viewmodel.VoteViewModel

class VoteFragment : Fragment() {

    private lateinit var binding: FragmentVoteBinding

    private val viewModel: VoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVoteBinding.inflate(inflater, container, false)

        return binding.root
    }
}