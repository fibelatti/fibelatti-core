package com.fibelatti.core.platform

import com.fibelatti.core.BaseJUnit4Test
import com.fibelatti.core.extension.mock
import com.fibelatti.core.extension.shouldBe
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify

class EventTest : BaseJUnit4Test() {

    private val mockValue = true
    private lateinit var event: Event<Boolean>

    @Before
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
