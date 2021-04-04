package com.santiihoyos.characters.list.view.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class CharacterItemDecoration(
    private val mSizeGridSpacingPx: Int,
    private val mGridSize: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = mSizeGridSpacingPx
        outRect.left = mSizeGridSpacingPx
        outRect.right = mSizeGridSpacingPx
    }
}
