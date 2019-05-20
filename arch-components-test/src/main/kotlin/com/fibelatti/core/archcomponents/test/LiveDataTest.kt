package com.fibelatti.core.archcomponents.test

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

interface LiveDataTestJ4 {

    @Before
    fun setupLiveDataTest() {
        ArchTaskExecutor.getInstance()
            .setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

                override fun postToMainThread(runnable: Runnable) = runnable.run()

                override fun isMainThread(): Boolean = true
            })
    }

    @After
    fun tearDownLiveDataTest() {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}

interface LiveDataTestJ5 {

    @BeforeEach
    fun setupLiveDataTest() {
        ArchTaskExecutor.getInstance()
            .setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

                override fun postToMainThread(runnable: Runnable) = runnable.run()

                override fun isMainThread(): Boolean = true
            })
    }

    @AfterEach
    fun tearDownLiveDataTest() {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}
