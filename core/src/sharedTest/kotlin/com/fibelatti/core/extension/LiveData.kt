package com.fibelatti.core.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.fibelatti.core.android.Event
import org.junit.Assert
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.mockito.Mockito

infix fun <T> LiveData<T>.currentValueShouldBe(expectedValue: T) {
    var value: T? = null
    val observer = Observer<T> { value = it }

    observeForever(observer)
    Assert.assertEquals(expectedValue, value)
    removeObserver(observer)
}

infix fun <T> LiveData<Event<T>>.currentEventShouldBe(expectedValue: T) {
    currentValueShouldBe(Event(expectedValue))
}

fun <T> LiveData<T>.shouldNeverReceiveValues() {
    val observer = Mockito.spy(Observer<T> {})
    observeForever(observer)
    BDDMockito.verify(observer, Mockito.never()).onChanged(ArgumentMatchers.any())
    removeObserver(observer)
}
