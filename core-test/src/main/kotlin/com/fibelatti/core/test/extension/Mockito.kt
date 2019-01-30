@file:Suppress("UNCHECKED_CAST")

package com.fibelatti.core.test.extension

import org.mockito.Mockito

/**
 * Shorthand to infer the type of a mocked class.
 *
 * val foo = mock<Foo>()
 *
 * @return a mock of [T]
 */
inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

/***
 * Mockito.any() returns null and that can be an issue when testing Kotlin code.
 * This function addresses that issue and enables the usage of this matcher.
 *
 * @return a non-null mock of [T]
 */
fun <T> safeAny(): T = Mockito.any<T>() ?: null as T
