package com.fibelatti.core.extension

import com.squareup.moshi.Moshi

inline fun <reified T> Moshi.toJson(value: T): String =
    adapter(T::class.java).toJson(value)

inline fun <reified T> Moshi.fromJson(value: String): T? =
    adapter(T::class.java).fromJson(value)
