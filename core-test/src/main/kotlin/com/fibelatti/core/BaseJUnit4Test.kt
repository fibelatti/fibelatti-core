package com.fibelatti.core

import com.fibelatti.core.provider.CoroutineLauncher
import com.fibelatti.core.provider.TestCoroutineLauncher

abstract class BaseJUnit4Test {

    protected val testCoroutineLauncher: CoroutineLauncher = TestCoroutineLauncher()
}
