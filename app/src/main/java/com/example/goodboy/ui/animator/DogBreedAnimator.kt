package com.example.goodboy.ui.animator

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

class DogBreedAnimator : DefaultItemAnimator() {

    override fun animateAppearance(
        viewHolder: RecyclerView.ViewHolder,
        preLayoutInfo: ItemHolderInfo?,
        postLayoutInfo: ItemHolderInfo
    ): Boolean {
        animateMove(viewHolder, 0, 0, postLayoutInfo.left, postLayoutInfo.top)
        runPendingAnimations()
        return true
    }
}