package com.fibelatti.core.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

interface CoroutinesTestJ4 {

    @Before
    fun setupCoroutinesTest() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDownCoroutinesTest() {
        Dispatchers.resetMain()
    }
}

interface CoroutinesTestJ5 {

    @BeforeEach
    fun setupCoroutinesTest() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterEach
    fun tearDownCoroutinesTest() {
        Dispatchers.resetMain()
    }
}
