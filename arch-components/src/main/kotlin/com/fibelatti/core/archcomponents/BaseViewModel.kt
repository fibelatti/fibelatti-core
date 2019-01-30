package com.fibelatti.core.archcomponents

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * [ViewModel] that also implements [CoroutineScope], allowing coroutines to be launched from it.
 *
 * All coroutine jobs will be cancelled when [onCleared] is called.
 */
abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val parentJob by lazy { Job() }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob

    val error: LiveData<Throwable> get() = _error
    private val _error by lazy { MutableLiveData<Throwable>() }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    protected fun handleError(error: Throwable) {
        _error.postValue(error)
    }
}
