package com.fibelatti.core.android.recyclerview

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration(private val itemOffset: Int) : RecyclerView.ItemDecoration() {

    constructor(context: Context, @DimenRes itemOffsetId: Int) : this(
        context.resources.getDimensionPixelSize(
            itemOffsetId
        )
    )

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val layoutParams = view.layoutParams

        when (layoutParams) {
            is GridLayoutManager.LayoutParams -> {
                layoutParams.apply {
                    if (viewLayoutPosition == RecyclerView.NO_POSITION) {
                        outRect.set(0, 0, 0, 0)
                        return
                    }

                    outRect.apply {
                        left = itemOffset / 2
                        top = itemOffset / 2
                        right = itemOffset / 2
                        bottom = itemOffset / 2
                    }
                }
            }
            is RecyclerView.LayoutParams -> {
                layoutParams.apply {
                    if (viewLayoutPosition == RecyclerView.NO_POSITION) {
                        outRect.set(0, 0, 0, 0)
                        return
                    }

                    outRect.apply {
                        left = 0
                        top = itemOffset / 2
                        right = 0
                        bottom = itemOffset / 2
                    }
                }
            }
        }
    }
}
