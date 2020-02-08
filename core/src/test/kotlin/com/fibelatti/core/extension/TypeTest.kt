package com.fibelatti.core.extension

import com.fibelatti.core.test.extension.shouldBe
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify

class TypeTest {

    @Nested
    inner class GenericExtensions {
        @Test
        fun `WHEN generic type is not null THEN orThrow returns the receiver type`() {
            // Arrange
            val someValue: String? = ""

            // Act
            val result = someValue.orThrow(Exception())

            // Assert
            assertEquals(someValue, result)
        }

        @Suppress("IMPLICIT_NOTHING_AS_TYPE_PARAMETER")
        @Test
        fun `WHEN generic type is null THEN orThrow throws exception`() {
            assertThrows<Exception> { null.orThrow(Exception()) }
        }
    }

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
        fun `WHEN ifNotNullOrEmpty is called AND string is null THEN nothing happens`() {
            // Arrange
            val mockFunction = spy {}
            val receiver: String? = null

            // Act
            receiver.ifNotNullOrEmpty { mockFunction() }

            // Assert
            verify(mockFunction, Mockito.never()).invoke()
        }

        @Test
        fun `WHEN ifNotNullOrEmpty is called AND string is empty THEN nothing happens`() {
            // Arrange
            val mockFunction = spy {}
            val receiver: String? = ""

            // Act
            receiver.ifNotNullOrEmpty { mockFunction() }

            // Assert
            verify(mockFunction, Mockito.never()).invoke()
        }

        @Test
        fun `WHEN ifNotNullOrEmpty is called AND string is not null or empty THEN block is invoked`() {
            // Arrange
            val mockFunction = spy {}
            val receiver: String? = "abc"

            // Act
            receiver.ifNotNullOrEmpty { mockFunction() }

            // Assert
            verify(mockFunction).invoke()
        }

        @Test
        fun `WHEN string is an int THEN isInt returns true`() {
            "10".isInt() shouldBe true
        }

        @Test
        fun `WHEN string is not an int THEN isInt returns false`() {
            "abc".isInt() shouldBe false
        }

        @Test
        fun `WHEN remove is called THEN all instances of the value are removed from the string`() {
            // Arrange
            val value = "abcabc"

            // Act & Assert
            assertEquals("bcbc", value.remove("a"))
            assertEquals("bcbc", value.remove("a".toRegex()))
        }

        @Test
        fun `WHEN removeFirst is called THEN the first instance of the value is removed from the string`() {
            // Arrange
            val value = "abcabc"

            // Act & Assert
            assertEquals("bcabc", value.removeFirst("a"))
            assertEquals("bcabc", value.removeFirst("a".toRegex()))
        }

        @Test
        fun `WHEN equalsIgnoreCase is called AND the strings are equal but the casing THEN true is returned`() {
            assertTrue("ABC".equalsIgnoreCase("abc"))
        }
    }

    @Nested
    inner class CharSequenceExtensions {
        @Test
        fun `WHEN osEmpty is called AND receiver type is null THEN empty string is returned`() {
            // Arrange
            val value: CharSequence? = null

            // Act & Assert
            assertTrue(value.orEmpty().isEmpty())
        }

        @Test
        fun `WHEN osEmpty is called AND receiver type is not null THEN the receiver type is returned as a string`() {
            // Arrange
            val value: CharSequence? = "abc"

            // Act & Assert
            assertEquals("abc", value.orEmpty())
        }

        @Test
        fun `WHEN remove is called THEN all instances of the value are removed from the string`() {
            // Arrange
            val value: CharSequence = "abcabc"

            // Act & Assert
            assertEquals("bcbc", value.remove("a".toRegex()))
        }

        @Test
        fun `WHEN removeFirst is called THEN the first instance of the value is removed from the string`() {
            // Arrange
            val value: CharSequence = "abcabc"

            // Act & Assert
            assertEquals("bcabc", value.removeFirst("a".toRegex()))
        }
    }
}
