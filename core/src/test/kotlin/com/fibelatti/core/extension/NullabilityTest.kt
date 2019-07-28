package com.fibelatti.core.extension

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyZeroInteractions

class NullabilityTest {

    @Nested
    inner class SafeLetTests {
        private val mockFn = spy {}
        private val notNullParam = "test"
        private val nullParam = null

        @Nested
        inner class TwoParameters {

            @Test
            fun `GIVEN all parameters are not null THEN block is invoked`() {
                // WHEN
                safeLet(notNullParam, notNullParam) { _, _ ->
                    mockFn()
                }

                // THEN
                verify(mockFn).invoke()
            }

            @Test
            fun `GIVEN any parameter is null THEN block is not invoked`() {
                // WHEN
                safeLet(nullParam, notNullParam) { _, _ ->
                    mockFn()
                }

                // THEN
                verifyZeroInteractions(mockFn)
            }
        }

        @Nested
        inner class ThreeParameters {

            @Test
            fun `GIVEN all parameters are not null THEN block is invoked`() {
                // WHEN
                safeLet(notNullParam, notNullParam, notNullParam) { _, _, _ ->
                    mockFn()
                }

                // THEN
                verify(mockFn).invoke()
            }

            @Test
            fun `GIVEN any parameter is null THEN block is not invoked`() {
                // WHEN
                safeLet(nullParam, notNullParam, notNullParam) { _, _, _ ->
                    mockFn()
                }

                // THEN
                verifyZeroInteractions(mockFn)
            }
        }

        @Nested
        inner class FourParameters {

            @Test
            fun `GIVEN all parameters are not null THEN block is invoked`() {
                // WHEN
                safeLet(notNullParam, notNullParam, notNullParam, notNullParam) { _, _, _, _ ->
                    mockFn()
                }

                // THEN
                verify(mockFn).invoke()
            }

            @Test
            fun `GIVEN any parameter is null THEN block is not invoked`() {
                // WHEN
                safeLet(nullParam, notNullParam, notNullParam, notNullParam) { _, _, _, _ ->
                    mockFn()
                }

                // THEN
                verifyZeroInteractions(mockFn)
            }
        }

        @Nested
        inner class FiveParameters {

            @Test
            fun `GIVEN all parameters are not null THEN block is invoked`() {
                // WHEN
                safeLet(notNullParam, notNullParam, notNullParam, notNullParam, notNullParam) { _, _, _, _, _ ->
                    mockFn()
                }

                // THEN
                verify(mockFn).invoke()
            }

            @Test
            fun `GIVEN any parameter is null THEN block is not invoked`() {
                // WHEN
                safeLet(nullParam, notNullParam, notNullParam, notNullParam, notNullParam) { _, _, _, _, _ ->
                    mockFn()
                }

                // THEN
                verifyZeroInteractions(mockFn)
            }
        }
    }
}
