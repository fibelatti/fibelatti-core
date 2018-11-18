package com.fibelatti.core.extension

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TypeTest {

    @Nested
    inner class BooleanExtensions {
        @Test
        fun `WHEN boolean is null THEN orFalse returns false`() {
            // GIVEN
            val boolean: Boolean? = null

            // THEN
            boolean.orFalse() shouldBe false
        }

        @Test
        fun `WHEN boolean is null THEN orTrue returns true`() {
            // GIVEN
            val boolean: Boolean? = null

            // THEN
            boolean.orTrue() shouldBe true
        }
    }

    @Nested
    inner class StringExtensions {
        @Test
        fun `WHEN string is an int THEN isInt returns true`() {
            "10".isInt() shouldBe true
        }

        @Test
        fun `WHEN string is not an int THEN isInt returns false`() {
            "abc".isInt() shouldBe false
        }
    }
}
