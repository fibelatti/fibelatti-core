package com.fibelatti.core.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Shorthand function to perform a [FragmentTransaction] using the [FragmentManager] of the parent
 * [FragmentActivity].
 *
 * @param allowStateLoss if [FragmentTransaction.commitAllowingStateLoss] can be used in case
 * [FragmentManager.isStateSaved] is false
 * @param block the block to be invoked in the transaction
 */
inline fun Fragment.inTransaction(
    allowStateLoss: Boolean = false,
    block: FragmentTransaction.() -> Unit
) {
    activity?.inTransaction(allowStateLoss, block)
}

/**
 * Shorthand function to invoke [FragmentActivity.onBackPressed] of the parent [FragmentActivity].
 */
fun Fragment.navigateBack() {
    activity?.onBackPressed()
}

/**
 * Shorthand function to check if this [Fragment] is the last in the list of fragments of its parent [FragmentActivity].
 *
 * @receiver the [Fragment] to be checked
 *
 * @return true if the fragment is the last in the of fragments, false otherwise
 */
fun Fragment.isAtTheTop(): Boolean = activity?.isFragmentAtTheTop(this).orFalse()

/**
 * Shorthand function to create an instance of [Fragment] with type [T].
 *
 * @param T the type of the [Fragment] to be created
 *
 * @return the new instance
 */
@Suppress("UnsafeCallOnNullableType") // Safe to suppress since T : Fragment, which is not a primitive type
inline fun <reified T : Fragment> FragmentFactory.createInstance(): Fragment =
    instantiate(T::class.java.classLoader!!, T::class.java.name)
