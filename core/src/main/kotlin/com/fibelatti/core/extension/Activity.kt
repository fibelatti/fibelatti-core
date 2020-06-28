package com.fibelatti.core.extension

import android.app.Activity
import androidx.annotation.StringRes
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Shorthand function to perform a [FragmentTransaction] using the [FragmentManager] of this
 * [FragmentActivity].
 *
 * @param allowStateLoss if [FragmentTransaction.commitAllowingStateLoss] can be used in case
 * [FragmentManager.isStateSaved] is false
 * @param block the block to be invoked in the transaction
 */
inline fun FragmentActivity.inTransaction(
    allowStateLoss: Boolean = false,
    block: FragmentTransaction.() -> Unit
) {
    supportFragmentManager.beginTransaction().apply {
        block()

        if (!supportFragmentManager.isStateSaved) {
            commit()
        } else if (allowStateLoss) {
            commitAllowingStateLoss()
        }
    }
}

/**
 * Shorthand function to check if a [Fragment] is the last in the list of fragments of this [FragmentActivity].
 *
 * @param fragment the [Fragment] to be checked
 *
 * @return true if the fragment is the last in the of fragments, false otherwise
 */
fun FragmentActivity.isFragmentAtTheTop(fragment: Fragment): Boolean =
    supportFragmentManager.fragments.last() == fragment

/**
 * Shorthand function to create an instance of [Fragment] with type [T] using the [FragmentFactory]
 * of this [FragmentActivity].
 *
 * @param T the type of the [Fragment] to be created
 *
 * @return the new instance
 */
inline fun <reified T : Fragment> FragmentActivity.createFragment(): Fragment =
    supportFragmentManager.fragmentFactory.createInstance<T>()

/**
 * Creates an intent using [ShareCompat] to share the given [text] to other apps.
 *
 * @param title the [StringRes] of the title to be displayed in the chooser
 * @param text the text to be shared
 */
fun Activity.shareText(@StringRes title: Int, text: String) {
    ShareCompat.IntentBuilder.from(this)
        .setType("text/plain")
        .setChooserTitle(title)
        .setText(text)
        .startChooser()
}

/**
 * Creates an intent using [ShareCompat] to share the given [text] to other apps.
 *
 * @param title the text of the title to be displayed in the chooser
 * @param text the text to be shared
 */
fun Activity.shareText(title: String, text: String) {
    ShareCompat.IntentBuilder.from(this)
        .setType("text/plain")
        .setChooserTitle(title)
        .setText(text)
        .startChooser()
}
