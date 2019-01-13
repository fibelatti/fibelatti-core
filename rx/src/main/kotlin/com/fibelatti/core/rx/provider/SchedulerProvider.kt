package com.fibelatti.core.rx.provider

import android.os.Looper
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

interface SchedulerProvider {
    fun main(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler
}

@Singleton
class AppSchedulerProvider @Inject constructor() : SchedulerProvider {
    init {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            AndroidSchedulers.from(Looper.getMainLooper(), true)
        }
    }

    override fun main(): Scheduler = AndroidSchedulers.mainThread()

    override fun computation(): Scheduler = Schedulers.computation()

    override fun io(): Scheduler = Schedulers.io()
}
