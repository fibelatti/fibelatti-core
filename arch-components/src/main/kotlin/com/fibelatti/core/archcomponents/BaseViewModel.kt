package com.fibelatti.core.archcomponents

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

/**
 * [ViewModel] that also implements [CoroutineScope], allowing coroutines to be launched from it.
 *
 * All coroutine jobs will be cancelled when [onCleared] is called.
 */
abstract class BaseViewModel : ViewModel(), CoroutineScope {

    /**
     * A [SupervisorJob] that will be the parent of any coroutines launched by inheritors of this [ViewModel].
     */
    private val parentJob = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob

    /**
     * The public getter of [_error] that any [androidx.lifecycle.LifecycleOwner] can observe.
     */
    val error: LiveData<Throwable> get() = _error

    /**
     * A [LiveData] of [Throwable] that any subclass can use to post errors through [handleError].
     */
    private val _error = MutableLiveData<Throwable>()

    /**
     * Cancels the [parentJob].
     */
    @CallSuper
    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    /**
     * Calls [MutableLiveData.postValue] on [_error] with the received error as its argument.
     */
    protected fun handleError(error: Throwable) {
        _error.postValue(error)
    }
}
