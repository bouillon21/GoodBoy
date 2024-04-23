package com.example.goodboy.ui.vote.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.goodboy.R
import com.example.goodboy.data.model.Vote

class PreviewMyVoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    private val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)

    private val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)

    fun onBind(vote: Vote) {
        imageView.load(vote.image?.url) {
            crossfade(true)
            error(R.drawable.image_plug)
            listener(
                onSuccess = { _, _ ->
                    progressBar.isVisible = false
                },
                onError = { _, _ ->
                    progressBar.isVisible = false
                },
                onStart = {
                    progressBar.isVisible = true
                },
                onCancel = {
                    progressBar.isVisible = false
                },
            )
        }
        ratingBar.rating = vote.value?.div(2F) ?: 0F
    }
}