package com.fibelatti.core.archcomponents.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.fibelatti.core.archcomponents.Event
import com.fibelatti.core.archcomponents.EventObserver

/**
 * Shorthand function that will observe the liveData using `this` as the [LifecycleOwner]. If the emitted value is not
 * null then body is invoked with it as an argument.
 *
 * @param liveData the [LiveData] to be observed
 * @param body function to be invoked with the emitted value as a parameter
 */
inline fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, crossinline body: (T) -> Unit) {
    liveData.observe(this, Observer { it?.let(body) })
}

/**
 * Shorthand function that will observe the liveData using `this` as the [LifecycleOwner]. It uses [EventObserver] to
 * observe the emitted values.
 *
 * @param liveData the [LiveData] of [Event] to be observed
 * @param body function to be invoked with the emitted value as a parameter
 */
fun <T : Any, L : LiveData<Event<T>>> LifecycleOwner.observeEvent(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, EventObserver(body))

/**
 * Shorthand function that will observe the [liveData] using `this` as the [LifecycleOwner], calling [body] only
 * if the emitted event is not null.
 *
 * @param liveData [LiveData] of [Throwable] to be observed
 * @param body function to be invoked with the emitted event as a parameter
 */
fun <L : LiveData<Throwable>> LifecycleOwner.error(liveData: L, body: (Throwable) -> Unit) =
    liveData.observe(this, Observer { it?.let(body) })
