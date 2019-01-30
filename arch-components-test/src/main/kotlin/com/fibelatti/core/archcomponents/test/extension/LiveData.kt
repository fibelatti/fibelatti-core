package com.fibelatti.core.archcomponents.test.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.fibelatti.core.archcomponents.Event
import org.junit.Assert.assertEquals
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.verify

/**
 * Shorthand function to test [LiveData]. It checks whether the current value is equals to [expectedValue].
 */
infix fun <T> LiveData<T>.currentValueShouldBe(expectedValue: T) {
    var value: T? = null
    val observer = Observer<T> { value = it }

    observeForever(observer)
    assertEquals(expectedValue, value)
    removeObserver(observer)
}

/**
 * Shorthand function to test [LiveData] of [Event]. It checks whether the current value is equals to [expectedValue].
 */
infix fun <T> LiveData<Event<T>>.currentEventShouldBe(expectedValue: T) {
    currentValueShouldBe(Event(expectedValue))
}

/**
 * Shorthand to assert that target [LiveData] never received any events.
 */
fun <T> LiveData<T>.shouldNeverReceiveValues() {
    val observer = Mockito.spy(Observer<T> {})
    observeForever(observer)
    verify(observer, Mockito.never()).onChanged(any())
    removeObserver(observer)
}
