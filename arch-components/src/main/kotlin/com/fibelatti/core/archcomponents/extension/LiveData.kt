package com.fibelatti.core.archcomponents.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fibelatti.core.archcomponents.Event
import com.fibelatti.core.archcomponents.LiveEvent
import com.fibelatti.core.archcomponents.MutableLiveEvent

/**
 * Creates an instance of [LiveData] with `this` as its initial value.
 *
 * @return a [LiveData] of [T], with `this` as its initial value
 */
fun <T> T?.asLiveData(): LiveData<T> = MutableLiveData<T>().apply { value = this@asLiveData }

/**
 * Creates an instance of [LiveEvent] with `this` wrapped as an [Event] as its initial value.
 *
 * @return a [LiveEvent] of [T] with `this` as its initial value
 */
fun <T> T?.asLiveEvent(): LiveEvent<T> = MutableLiveEvent<T>().apply { value = Event(this@asLiveEvent) }
