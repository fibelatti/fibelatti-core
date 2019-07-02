package com.fibelatti.core.functional

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

/**
 * A service class to execute tasks sequentially using coroutines.
 *
 * [afterPrevious] will always ensure that all previously requested work has finished prior to running the given block,
 * and that future work will wait until the current work is finished before starting.
 */
class SingleRunner @Inject constructor() {

    /**
     * A coroutine [Mutex] implements a lock that may only be taken by one coroutine at a time.
     */
    private val mutex = Mutex()

    /**
     * Ensures that the block will only be executed after all previous work has completed.
     *
     * When several coroutines call afterPrevious at the same time, they will queue up in the order
     * that they call afterPrevious. Then, one coroutine will enter the block at a time.
     *
     * @param block the suspend function to be executed after the previous work is finished.
     */
    suspend fun <T> afterPrevious(block: suspend () -> T): T = mutex.withLock { block() }
}
