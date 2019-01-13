package com.fibelatti.core.test

import com.fibelatti.core.provider.CoroutineLauncher
import com.fibelatti.core.provider.TestCoroutineLauncher
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseJUnit5Test {

    protected val testCoroutineLauncher: CoroutineLauncher =
        TestCoroutineLauncher()
}
