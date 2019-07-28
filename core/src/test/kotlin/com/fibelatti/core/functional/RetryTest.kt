package com.fibelatti.core.functional

import com.fibelatti.core.test.extension.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.spy
import java.io.IOException

class RetryTest {

    @Nested
    inner class NetworkRetryTests {

        private val mockFn = spy(MockFunctions())

        @Test
        fun `GIVEN the block threw IOException WHEN a retry is successful before maxDelay THEN the result is returned`() {
            // GIVEN
            given(mockFn.default())
                .willThrow(IOException())
                .willCallRealMethod()

            // WHEN
            val result = runBlocking { retryIO { mockFn.default() } }

            // THEN
            result shouldBe Unit
        }

        @Test
        fun `GIVEN the block threw IOException WHEN no retry is successful before maxDelay THEN IOException is returned`() {
            // GIVEN
            given(mockFn.default())
                .willThrow(IOException())
                .willThrow(IOException())
                .willThrow(IOException())
                .willThrow(IOException())
                .willThrow(IOException())
                .willThrow(IOException())
                .willCallRealMethod()

            // THEN
            assertThrows<Exception> {
                runBlocking { retryIO { mockFn.default() } }
            }
        }
    }

    class MockFunctions {
        @Throws(IOException::class)
        fun default() = Unit
    }
}
