package com.fibelatti.core.test.extension

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

/**
 * Shorthand to throw assertion error with a default message.
 */
fun throwAssertionError() {
    throw AssertionError("The expected condition was not fulfilled.")
}

/**
 * Shorthand for [org.junit.Assert.assertEquals] to be written in a more natural way.
 *
 * foo shouldBe bar
 */
infix fun Any?.shouldBe(otherValue: Any?) {
    assertEquals(otherValue, this)
}

/**
 * Shorthand for [org.junit.Assert.assertTrue] when asserting a list size, to be written in a more natural way.
 *
 * foo sizeShouldBe 10
 */
infix fun <ListType, T : List<ListType>> T.sizeShouldBe(value: Int) {
    assertTrue(
        "Expected size: $value - Actual size: $size",
        size == value
    )
}

/**
 * Shorthand for [org.junit.Assert.assertTrue] when asserting that a list is empty, to be written in a more natural way.
 *
 * foo.shouldBeEmpty()
 */
fun <T> List<T>.shouldBeEmpty() {
    assertTrue(
        "Expected size: 0 - Actual size: $size",
        this.isEmpty()
    )
}

/**
 * Shorthand for [org.junit.Assert.assertTrue] when asserting that an object is an instance of [T].
 *
 * foo.shouldBeAnInstanceOf<String>()
 */
inline fun <reified T : Any> Any.shouldBeAnInstanceOf() {
    assertTrue(
        "Expected: ${T::class.java} - Actual: ${this::class.java}",
        this::class == T::class
    )
}

/**
 * Shorthand for [org.junit.Assert.assertTrue] when asserting that a list contains [value].
 *
 * foo shouldContain bar
 */
infix fun <T> List<T>.shouldContain(value: T) {
    assertTrue(contains(value))
}

/**
 * Shorthand for [org.junit.Assert.assertTrue] when asserting that a list contains all values of [subList], without
 * caring for the order of the elements.
 *
 * foo shouldContain listOf(bar, baz)
 */
infix fun <T> List<T>.shouldContain(subList: List<T>) {
    assertTrue(
        "Expected: $subList - Actual: $this",
        containsAll(subList)
    )
}
