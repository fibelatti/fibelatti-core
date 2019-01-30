package com.fibelatti.core.rx.provider

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Handy class for testing when injecting the schedulers. It always returns [Schedulers.trampoline] so there's no need
 * to override the schedulers when testing Rx code.
 */
class TestSchedulerProvider : SchedulerProvider {
    override fun main(): Scheduler = Schedulers.trampoline()

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun computation(): Scheduler = Schedulers.trampoline()
}
