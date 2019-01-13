@file:Suppress("UNCHECKED_CAST")

package com.fibelatti.core.test.extension

import org.mockito.Mockito

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

/***
 * Mockito.any() returns null and that can be an issue when testing Kotlin code.
 * This function addresses that issue and enables the usage of this matcher.
 */
fun <T> safeAny(): T = Mockito.any<T>() ?: null as T
