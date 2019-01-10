package com.fibelatti.core

import com.fibelatti.core.provider.SchedulerProvider
import com.fibelatti.core.provider.TestSchedulerProvider

abstract class BaseJUnit4Test {

    protected val testSchedulerProvider: SchedulerProvider = TestSchedulerProvider()
}
