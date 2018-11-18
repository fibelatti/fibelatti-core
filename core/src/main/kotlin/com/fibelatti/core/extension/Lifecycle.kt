package com.fibelatti.core.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.fibelatti.core.platform.Event
import com.fibelatti.core.platform.EventObserver

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, Observer { it?.let(body) })

fun <T : Any, L : LiveData<Event<T>>> LifecycleOwner.observeEvent(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, EventObserver(body))

fun <L : LiveData<Throwable>> LifecycleOwner.error(liveData: L, body: (Throwable) -> Unit) =
    liveData.observe(this, Observer { it?.let(body) })
