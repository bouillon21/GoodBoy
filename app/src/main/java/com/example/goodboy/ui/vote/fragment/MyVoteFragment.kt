package com.example.goodboy.ui.vote.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.goodboy.databinding.FragmentMyVoteBinding
import com.example.goodboy.ui.vote.viewmodel.MyVoteViewModel

class MyVoteFragment : Fragment() {

    private lateinit var binding: FragmentMyVoteBinding

    private val viewModel: MyVoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyVoteBinding.inflate(inflater, container, false)

        return binding.root
    }
}