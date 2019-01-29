package com.fibelatti.core.extension

import androidx.annotation.NonNull
import com.fibelatti.core.test.extension.shouldBe
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LibraryTest {

    @Nested
    inner class MoshiTests {

        private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        @Test
        fun `GIVEN fromJson is called AND json string is valid THEN the object is returned`() {
            // GIVEN
            val inputObject = SampleObject("test")
            val input = moshi.toJson(inputObject)

            // WHEN
            val result: SampleObject? = moshi.fromJson(input)

            // THEN
            result shouldBe inputObject
        }

        @Test
        fun `GIVEN fromJson is called AND json string is invalid THEN exception is thrown`() {
            // GIVEN
            val invalidInput = """{"xyz":"abc"}"""

            // THEN
            assertThrows<JsonDataException> {
                moshi.fromJson<SampleObject>(invalidInput)
            }
        }
    }

    private data class SampleObject(@NonNull val someProperty: String)
}
