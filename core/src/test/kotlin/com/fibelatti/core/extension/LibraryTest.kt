package com.fibelatti.core.extension

import androidx.annotation.NonNull
import com.google.gson.Gson
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class LibraryTest {

    @Nested
    inner class GsonTests {

        @Test
        fun `GIVEN fromJsonIsCalled THEN the object is returned`() {
            // GIVEN
            val gson = Gson()
            val inputObject = SampleObject("test")
            val input = gson.toJson(inputObject)

            // WHEN
            val result: SampleObject = gson.fromJson(input)

            // THEN
            result shouldBe inputObject
        }
    }

    private data class SampleObject(@NonNull val someProperty: String)
}
