package com.fibelatti.core.extension

import com.squareup.moshi.Moshi

/**
 * Creates a [com.squareup.moshi.JsonAdapter] out of [T] and calls toJson with [value] as its
 * parameter.
 *
 * @param value an instance of [T]
 *
 * @return a json string of [T]
 */
inline fun <reified T> Moshi.toJson(value: T): String =
    adapter(T::class.java).toJson(value)

/**
 * Creates a [com.squareup.moshi.JsonAdapter] out of [T] and calls fromJson with [value] as its
 * parameter.
 *
 * @param value a json string of [T]
 *
 * @return an instance of [T] if successful, null otherwise
 */
inline fun <reified T> Moshi.fromJson(value: String): T? =
    adapter(T::class.java).fromJson(value)
