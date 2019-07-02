package com.fibelatti.core.android

import com.fibelatti.core.InstantExecutorExtension
import com.fibelatti.core.archcomponents.Event
import com.fibelatti.core.archcomponents.EventObserver
import com.fibelatti.core.archcomponents.MutableLiveEvent
import com.fibelatti.core.test.extension.mock
import com.fibelatti.core.test.extension.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.verify

@ExtendWith(InstantExecutorExtension::class)
class EventTest {

    private val mockValue = true
    private lateinit var event: Event<Boolean>

    @BeforeEach
    fun setup() {
        event = Event(mockValue)
    }

    @Test
    fun whenGetContentIsCalledForTheFirstTimeThenValueIsReturned() {
        event.getContent() shouldBe mockValue
    }

    @Test
    fun givenGetContentWasAlreadyCalledWhenGetContentIsCalledAgainThenNullIsReturned() {
        event.run {
            getContent() shouldBe mockValue
            getContent() shouldBe null
        }
    }

    @Test
    fun givenGetContentWasAlreadyCalledWhenPeekContentIsCalledThenValueIsReturned() {
        event.run {
            getContent() shouldBe mockValue
            getContent() shouldBe null
            peekContent() shouldBe mockValue
        }
    }

    @Test
    fun whenPostValueIsCalledThenObserverFunctionIsCalled() {
        // GIVEN
        val mockOnEvent = mock<(Boolean) -> Unit>()
        val liveEvent = MutableLiveEvent<Boolean>().apply {
            observeForever(EventObserver(mockOnEvent))
        }

        // WHEN
        liveEvent.postValue(event)

        // THEN
        verify(mockOnEvent).invoke(mockValue)
    }

    @Test
    fun givenPostValueWasAlreadyCalledWhenPostValueIsCalledAgainThenObserverFunctionIsNotCalledAgain() {
        // GIVEN
        val mockOnEvent = mock<(Boolean) -> Unit>()
        val liveEvent = MutableLiveEvent<Boolean>().apply {
            observeForever(EventObserver(mockOnEvent))
        }

        // WHEN
        liveEvent.postValue(event)
        liveEvent.postValue(event)

        // THEN
        verify(mockOnEvent).invoke(mockValue)
    }
}
