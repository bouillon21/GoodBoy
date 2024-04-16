package com.example.goodboy.ui.breed.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class OffsetItemDecorator(
    private val leftOffSet: Int = 0,
    private val topOffSet: Int = 0,
    private val rightOffSet: Int = 0,
    private val bottomOffSet: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(leftOffSet, topOffSet, rightOffSet, bottomOffSet)
    }
}