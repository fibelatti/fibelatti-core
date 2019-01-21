package com.fibelatti.core.archcomponents.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.fibelatti.core.archcomponents.Event
import com.fibelatti.core.archcomponents.EventObserver

inline fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, crossinline body: (T) -> Unit) =
    liveData.observe(this, Observer { it?.let(body) })

fun <T : Any, L : LiveData<Event<T>>> LifecycleOwner.observeEvent(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, EventObserver(body))

fun <L : LiveData<Throwable>> LifecycleOwner.error(liveData: L, body: (Throwable) -> Unit) =
    liveData.observe(this, Observer { it?.let(body) })
