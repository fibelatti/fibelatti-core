package com.fibelatti.core.test.extension

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.mockito.BDDMockito
import org.mockito.verification.VerificationMode

/**
 * Shorthand to wrap [BDDMockito.given] call with [runBlocking], being able to write tests using a familiar syntax.
 *
 * givenSuspend { foo.bar() }.willReturn(baz)
 *
 * @param body a block of code to be invoked within [runBlocking]
 *
 * @return an instance of [BDDMockito.BDDMyOngoingStubbing] of [T]
 */
fun <T> givenSuspend(body: suspend () -> T): BDDMockito.BDDMyOngoingStubbing<T> =
    BDDMockito.given(runBlocking { body() })

/**
 * Shorthand to wrap [value] to be returned as the result of [BDDMockito.willReturn] with [CompletableDeferred],
 * being able to write tests using a similar syntax.
 *
 * givenSuspend { foo.bar() }.willReturnDeferred(baz)
 *
 * @param value the value to be returned wrapped in a [CompletableDeferred]
 */
fun <T> BDDMockito.BDDMyOngoingStubbing<Deferred<T>>.willReturnDeferred(value: T) {
    willReturn(CompletableDeferred(value))
}

/**
 * Shorthand to wrap [value] to be returned as the result of [BDDMockito.willReturn] with [CompletableDeferred] that
 * will always fail, being able to write tests using a similar syntax.
 *
 * givenSuspend { foo.bar() }.willReturnFailedDeferred(Exception())
 *
 * @param value the value to be returned wrapped in a [CompletableDeferred] that will
 * [CompletableDeferred.completeExceptionally]
 */
fun <T> BDDMockito.BDDMyOngoingStubbing<Deferred<T>>.willReturnFailedDeferred(value: Throwable) {
    willReturn(CompletableDeferred<T>().apply { completeExceptionally(value) })
}

/**
 * Shorthand to verify that suspend functions were called in a mock, being able to write tests using a similar syntax.
 *
 * verifySuspend(foo) { bar() }
 *
 * @param mock an [org.mockito.stubbing.OngoingStubbing] to be verified
 * @param methodCall the method of [T] that should have been called
 */
fun <T> verifySuspend(mock: T, methodCall: suspend T.() -> Any?) {
    runBlocking { BDDMockito.verify(mock).run { methodCall() } }
}

/**
 * Shorthand to verify that suspend functions were called in a mock according to the [verificationMode],
 * being able to write tests using a similar syntax.
 *
 * verifySuspend(foo, never()) { bar() }
 *
 * @param mock an [org.mockito.stubbing.OngoingStubbing] to be verified
 * @param verificationMode the [VerificationMode] to be applied to [BDDMockito.verify]
 * @param methodCall the method of [T] that should have been called according to the received verificationMode
 */
fun <T> verifySuspend(mock: T, verificationMode: VerificationMode, methodCall: suspend T.() -> Any?) {
    runBlocking { BDDMockito.verify(mock, verificationMode).run { methodCall() } }
}
