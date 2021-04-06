package com.example.nouste.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class GridSpacingItemDecorator(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex
        val column = position % spanCount

        if (includeEdge) {
            outRect.left = spacing - column * spacing

            if (position < spanCount) outRect.top = spacing
            outRect.bottom = spacing / 2
        } else {
            outRect.left = column * spacing / spanCount
            if (position >= spanCount) outRect.top = spacing / 2
        }
    }

}