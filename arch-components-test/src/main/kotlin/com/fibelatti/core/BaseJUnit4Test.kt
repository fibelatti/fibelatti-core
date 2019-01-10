package com.fibelatti.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class BaseJUnit4Test {

    /* ViewModel tests should use jUnit 4 as rules are not supported in jUnit 5 */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
}
