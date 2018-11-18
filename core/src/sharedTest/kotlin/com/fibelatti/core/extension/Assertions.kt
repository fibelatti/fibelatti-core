package com.fibelatti.core.extension

import junit.framework.AssertionFailedError
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

fun throwAssertionError() {
    throw AssertionFailedError("The expected condition was not fulfilled.")
}

infix fun Any?.shouldBe(otherValue: Any?) {
    assertEquals(otherValue, this)
}

infix fun <ListType, T : List<ListType>> T.sizeShouldBe(value: Int) {
    assertTrue(
        "Expected size: $value - Actual size: $size",
        size == value
    )
}

fun <T> List<T>.shouldBeEmpty() {
    assertTrue(
        "Expected size: 0 - Actual size: $size",
        this.isEmpty()
    )
}

inline fun <reified T : Any> Any.shouldBeAnInstanceOf() {
    assertTrue(
        "Expected: ${T::class.java} - Actual: ${this::class.java}",
        this::class == T::class
    )
}

infix fun <T> List<T>.shouldContain(value: T) {
    assertTrue(contains(value))
}

infix fun <T> List<T>.shouldContain(subList: List<T>) {
    assertTrue(
        "Expected: $subList - Actual: $this",
        containsAll(subList)
    )
}
