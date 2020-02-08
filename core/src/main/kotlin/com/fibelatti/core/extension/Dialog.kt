package com.fibelatti.core.extension

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Shorthand function to show a styled [AlertDialog].
 *
 * @param dialogStyle the style resource
 * @param dialogBackground the drawable resource to be used as background
 * @param configuration optional additional configuration
 */
fun Context.showStyledDialog(
    @StyleRes dialogStyle: Int,
    @DrawableRes dialogBackground: Int,
    configuration: MaterialAlertDialogBuilder.() -> Unit
) {
    MaterialAlertDialogBuilder(this, dialogStyle)
        .setBackground(ContextCompat.getDrawable(this, dialogBackground))
        .apply(configuration)
        .show()
}
