package com.fibelatti.core.extension

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

/**
 * Shows a [Toast] with the given message.
 *
 * @param message the message to show
 * @param duration [Toast.LENGTH_SHORT] (default) or [Toast.LENGTH_LONG]
 */
fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

/**
 * Shows a style [Snackbar] with the given message.
 *
 * @param message the message to show
 * @param textColor optional color resource
 * @param marginSize optional dimen resource
 * @param background optional drawable resource
 * @param duration [Snackbar.LENGTH_SHORT] (default), [Snackbar.LENGTH_LONG] or [Snackbar.LENGTH_INDEFINITE]
 * @param additionalConfiguration optional additional configuration
 */
fun View.snackbar(
    message: String,
    @ColorRes textColor: Int = -1,
    @DimenRes marginSize: Int = -1,
    @DrawableRes background: Int = -1,
    duration: Int = Snackbar.LENGTH_SHORT,
    additionalConfiguration: Snackbar.() -> Unit = {}
) {
    val snackbar = Snackbar.make(this, message, duration)
    if (textColor != -1) {
        snackbar.setTextColor(ContextCompat.getColor(context, textColor))
    }
    if (marginSize != -1) {
        val margin = context.resources.getDimensionPixelSize(marginSize)
        snackbar.view.layoutParams = (snackbar.view.layoutParams as ViewGroup.MarginLayoutParams).apply {
            setMargins(margin, margin, margin, margin)
        }
    }
    if (background != -1) {
        snackbar.view.background = ContextCompat.getDrawable(context, background)
    }

    snackbar.additionalConfiguration()
    snackbar.show()
}
