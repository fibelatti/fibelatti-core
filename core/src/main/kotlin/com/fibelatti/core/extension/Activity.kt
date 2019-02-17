package com.fibelatti.core.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.isFragmentAtTheTop(fragment: Fragment): Boolean =
    supportFragmentManager.fragments.last() == fragment
