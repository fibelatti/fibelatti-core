package com.fibelatti.core.rx.provider

import android.os.Looper
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Interface to abstract Rx schedulers from source code.
 *
 * It's useful mainly for testing, since a different scheduler can be provided instead.
 */
interface SchedulerProvider {
    fun main(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler
}

/**
 * Default implementation of [SchedulerProvider].
 *
 * For [AndroidSchedulers.mainThread] the async messaging is used on API >= 16 to avoid VSYNC locking.
 * On API < 16 this value is ignored.
 */
@Singleton
class AppSchedulerProvider @Inject constructor() : SchedulerProvider {
    init {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            AndroidSchedulers.from(Looper.getMainLooper(), true)
        }
    }

    /**
     * @return [AndroidSchedulers.mainThread]
     */
    override fun main(): Scheduler = AndroidSchedulers.mainThread()

    /**
     * @return [Schedulers.computation]
     */
    override fun computation(): Scheduler = Schedulers.computation()

    /**
     * @return [Schedulers.io]
     */
    override fun io(): Scheduler = Schedulers.io()
}
