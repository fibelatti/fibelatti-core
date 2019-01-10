package com.fibelatti.core

import com.fibelatti.core.provider.SchedulerProvider
import com.fibelatti.core.provider.TestSchedulerProvider
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseJUnit5Test {

    protected val testSchedulerProvider: SchedulerProvider = TestSchedulerProvider()
}
