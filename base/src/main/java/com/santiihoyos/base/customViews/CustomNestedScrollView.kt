package com.santiihoyos.base.customViews

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.NestedScrollView

const val SCROLL_UP = 1
const val SCROLL_DOWN = 2

class CustomNestedScrollView : NestedScrollView {

    lateinit var scrollDirectionListener: (scrollDirection: Int) -> Unit
    lateinit var scrollListener: OnNestedScrollChangeState

    private var lastScrollUpdate: Long = -1
    private var currentX = 0

    private var previousY = 0
    private var currentY = 0

    interface OnNestedScrollChangeState {
        fun onScrollStart(x: Int, y: Int)
        fun onScrollEnd(x: Int, y: Int)
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private fun stateHandler() {

        val currentTime = System.currentTimeMillis()
        if (currentTime - lastScrollUpdate > 300) {
            lastScrollUpdate = -1
            if (::scrollDirectionListener.isInitialized) {
                scrollDirectionListener(if (currentY < previousY) SCROLL_UP else SCROLL_DOWN)
            }
            if (::scrollListener.isInitialized) {
                scrollListener.onScrollEnd(currentX, currentY)
            }
        } else {
            postDelayed(::stateHandler, 300)
        }
    }

    override fun onScrollChanged(currentX: Int, currentY: Int, oldX: Int, oldY: Int) {

        super.onScrollChanged(currentX, currentY, oldX, oldY)
        this.currentX = currentX
        this.currentY = currentY
        if (lastScrollUpdate == -1L) {
            previousY = oldY
            if (::scrollListener.isInitialized) {
                scrollListener.onScrollStart(oldX, oldY)
            }
            postDelayed(::stateHandler, 300)
        }
        lastScrollUpdate = System.currentTimeMillis()
    }
}
