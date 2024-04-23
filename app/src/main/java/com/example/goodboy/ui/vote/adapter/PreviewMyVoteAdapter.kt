package com.example.goodboy.ui.vote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.goodboy.R
import com.example.goodboy.data.model.Vote
import com.example.goodboy.ui.diffutil.DiffUtilCallbackImp
import com.example.goodboy.ui.vote.viewholder.PreviewMyVoteViewHolder

class PreviewMyVoteAdapter : RecyclerView.Adapter<PreviewMyVoteViewHolder>() {

    var votes = ArrayList<Vote>()
        set(value) {
            val callback = DiffUtilCallbackImp(
                oldItems = field,
                newItems = value,
                { oldItem: Vote, newItem -> oldItem.id == newItem.id },
                { oldItem: Vote, newItem -> oldItem.value == newItem.value }
            )
            field = value
            val diffUtil = DiffUtil.calculateDiff(callback)
            diffUtil.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewMyVoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PreviewMyVoteViewHolder(
            layoutInflater.inflate(
                R.layout.vote_item_preview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = votes.size

    override fun onBindViewHolder(holder: PreviewMyVoteViewHolder, position: Int) {
        holder.onBind(votes[position])
    }
}