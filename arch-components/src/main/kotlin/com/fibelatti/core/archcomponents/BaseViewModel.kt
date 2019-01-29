package com.fibelatti.core.archcomponents

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val parentJob by lazy { Job() }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob

    val error by lazy { MutableLiveData<Throwable>() }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    protected fun handleError(error: Throwable) {
        this.error.postValue(error)
    }
}
