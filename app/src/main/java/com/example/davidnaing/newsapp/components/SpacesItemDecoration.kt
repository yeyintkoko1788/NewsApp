package com.example.davidnaing.newsapp.components

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


/**
 * Created by yepyaesonetun on 7/23/18.
 **/
class SpacesItemDecoration(space: Int) : RecyclerView.ItemDecoration() {

    private val halfSpace: Int

    init {
        this.halfSpace = space / 2
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        if (parent.paddingLeft != halfSpace) {
            parent.setPadding(halfSpace, halfSpace, halfSpace, halfSpace)
            parent.clipToPadding = false
        }

        outRect.top = halfSpace
        outRect.bottom = halfSpace
        outRect.left = halfSpace
        outRect.right = halfSpace
    }
}
