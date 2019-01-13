package com.fibelatti.core.android.base

import androidx.annotation.CallSuper
import com.fibelatti.core.rx.provider.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V : BaseContract.View>(
    protected val schedulerProvider: SchedulerProvider
) {
    private val viewDisposables = CompositeDisposable()
    protected var view: V? = null

    fun attachView(view: V) {
        this.view = view
    }

    @CallSuper
    open fun detachView() {
        view = null
        viewDisposables.clear()
    }

    protected fun handleError(error: Throwable) {
        view?.handleError(error)
    }

    protected fun Disposable.disposeOnDetach() {
        viewDisposables.add(this)
    }
}

interface BaseContract {
    interface View {
        fun handleError(error: Throwable)
    }
}
