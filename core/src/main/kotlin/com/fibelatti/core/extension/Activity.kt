package com.fibelatti.core.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Shorthand function to check if a [Fragment] is the last in the list of fragments of this [FragmentActivity].
 *
 * @param fragment the [Fragment] to be checked
 *
 * @return true if the fragment is the last in the of fragments, false otherwise
 */
fun FragmentActivity.isFragmentAtTheTop(fragment: Fragment): Boolean =
    supportFragmentManager.fragments.last() == fragment
