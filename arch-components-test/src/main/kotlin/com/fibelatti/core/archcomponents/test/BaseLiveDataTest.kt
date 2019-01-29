package com.fibelatti.core.archcomponents.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class BaseLiveDataTest {

    /* ViewModel tests should use jUnit 4 as rules are not supported in jUnit 5 */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
}
