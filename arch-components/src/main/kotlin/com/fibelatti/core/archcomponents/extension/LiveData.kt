package com.fibelatti.core.archcomponents.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fibelatti.core.archcomponents.Event
import com.fibelatti.core.archcomponents.LiveEvent
import com.fibelatti.core.archcomponents.MutableLiveEvent

fun <T> T?.asLiveData(): LiveData<T> = MutableLiveData<T>().apply { value = this@asLiveData }

fun <T> T?.asLiveEvent(): LiveEvent<T> = MutableLiveEvent<T>().apply { value =
    Event(this@asLiveEvent)
}
