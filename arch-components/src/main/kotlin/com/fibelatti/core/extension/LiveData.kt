package com.fibelatti.core.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fibelatti.core.android.Event
import com.fibelatti.core.android.LiveEvent
import com.fibelatti.core.android.MutableLiveEvent

fun <T> T?.asLiveData(): LiveData<T> = MutableLiveData<T>().apply { value = this@asLiveData }

fun <T> T?.asLiveEvent(): LiveEvent<T> = MutableLiveEvent<T>().apply { value = Event(this@asLiveEvent) }
