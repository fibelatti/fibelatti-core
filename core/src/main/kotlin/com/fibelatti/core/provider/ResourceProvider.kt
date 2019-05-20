package com.fibelatti.core.provider

import androidx.annotation.StringRes

/**
 * Handy interface to abstract the Android Framework in ViewModels, Presenters, etc. With this it is possible to
 * retrieve string resources in scenarios which there's logic involved, improving testability.
 */
interface ResourceProvider {
    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String

    fun getJsonFromAssets(fileName: String): String?
}
