package com.fibelatti.core.extension

import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.widget.ScrollView
import androidx.core.view.NestedScrollingParent
import androidx.recyclerview.widget.RecyclerView

/**
 * Helper function to search for a scrolling view which window insets should be applied.
 *
 * @param rootView the root [View] to start the search
 */
fun getViewToApplyInsets(rootView: View): View {
    return when (rootView) {
        is ScrollView,
        is NestedScrollingParent -> (rootView as? ViewGroup)?.children?.lastOrNull()
        is RecyclerView -> rootView
        is ViewGroup -> {
            rootView.children.lastOrNull {
                it is ScrollView || it is NestedScrollingParent || it is RecyclerView
            }?.let(::getViewToApplyInsets)
        }
        else -> rootView
    } ?: rootView
}

/**
 * Helper function to manage callbacks of [View.setOnApplyWindowInsetsListener].
 *
 * @param f the function to be invoked when [View.setOnApplyWindowInsetsListener] is called.
 */
fun View.doOnApplyWindowInsets(f: (View, WindowInsets, InitialPadding, InitialMargin) -> Unit) {

    val initialPadding = InitialPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
    val initialMargin = (layoutParams as? ViewGroup.MarginLayoutParams)
        ?.run { InitialMargin(leftMargin, topMargin, rightMargin, bottomMargin) }
        ?: InitialMargin(0, 0, 0, 0)

    setOnApplyWindowInsetsListener { v, insets ->
        f(v, insets, initialPadding, initialMargin)
        insets
    }
}

/**
 * The initial padding values of a [View].
 *
 * @see [View.doOnApplyWindowInsets]
 */
data class InitialPadding(val left: Int, val top: Int, val right: Int, val bottom: Int)

/**
 * The initial margin values of a [View].
 *
 * @see [View.doOnApplyWindowInsets]
 */
data class InitialMargin(val left: Int, val top: Int, val right: Int, val bottom: Int)
