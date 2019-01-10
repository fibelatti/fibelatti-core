package com.fibelatti.core.provider

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String

    fun <T> getJsonFromAssets(fileName: String, clazz: Class<T>): T?
}
