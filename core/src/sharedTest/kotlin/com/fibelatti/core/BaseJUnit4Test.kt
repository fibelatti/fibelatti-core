package com.fibelatti.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fibelatti.core.provider.CoroutineLauncher
import com.fibelatti.core.provider.SchedulerProvider
import com.fibelatti.core.provider.TestCoroutineLauncher
import com.fibelatti.core.provider.TestSchedulerProvider
import org.junit.Rule

abstract class BaseJUnit4Test {

    /* ViewModel tests should use jUnit 4 as rules are not supported in jUnit 5 */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    protected val testCoroutineLauncher: CoroutineLauncher = TestCoroutineLauncher()

    protected val testSchedulerProvider: SchedulerProvider = TestSchedulerProvider()
}
