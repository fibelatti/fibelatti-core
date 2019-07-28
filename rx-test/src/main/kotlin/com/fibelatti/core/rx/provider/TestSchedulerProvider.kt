package com.fibelatti.core.rx.provider

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Handy class for testing when injecting the schedulers. All methods return [Schedulers.trampoline] so there's no need
 * to override the schedulers when testing Rx code.
 */
class TestSchedulerProvider : SchedulerProvider {

    /**
     * @return [Schedulers.trampoline]
     */
    override fun main(): Scheduler = Schedulers.trampoline()

    /**
     * @return [Schedulers.trampoline]
     */
    override fun io(): Scheduler = Schedulers.trampoline()

    /**
     * @return [Schedulers.trampoline]
     */
    override fun computation(): Scheduler = Schedulers.trampoline()
}
