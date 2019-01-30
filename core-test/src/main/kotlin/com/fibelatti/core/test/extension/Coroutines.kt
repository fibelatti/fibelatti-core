package com.fibelatti.core.test.extension

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.mockito.BDDMockito
import org.mockito.verification.VerificationMode

/**
 * Shorthand to wrap [BDDMockito.given] call with [runBlocking], being able to write tests using a similar syntax.
 *
 * givenSuspend { foo.bar() }.willReturn(baz)
 */
fun <T> givenSuspend(methodCall: suspend () -> T): BDDMockito.BDDMyOngoingStubbing<T> =
    BDDMockito.given(runBlocking { methodCall() })

/**
 * Shorthand to wrap call with [runBlocking], being able to write tests using a similar syntax.
 *
 * val result = callSuspend { foo.bar() }
 */
fun <T> callSuspend(methodCall: suspend () -> T): T = runBlocking { methodCall() }

/**
 * Shorthand to wrap [value] to be returned as the result of [BDDMockito.willReturn] with [CompletableDeferred],
 * being able to write tests using a similar syntax.
 *
 * givenSuspend { foo.bar() }.willReturnDeferred(baz)
 */
fun <T> BDDMockito.BDDMyOngoingStubbing<Deferred<T>>.willReturnDeferred(value: T) {
    willReturn(CompletableDeferred(value))
}

/**
 * Shorthand to wrap [value] to be returned as the result of [BDDMockito.willReturn] with [CompletableDeferred] that
 * will always fail, being able to write tests using a similar syntax.
 *
 * givenSuspend { foo.bar() }.willReturnFailedDeferred(Exception())
 */
fun <T> BDDMockito.BDDMyOngoingStubbing<Deferred<T>>.willReturnFailedDeferred(value: Throwable) {
    willReturn(CompletableDeferred<T>().apply { completeExceptionally(value) })
}

/**
 * Shorthand to verify that suspend functions were called in a mock, being able to write tests using a similar syntax.
 *
 * verifySuspend(foo) { bar() }
 */
fun <T> verifySuspend(mock: T, methodCall: suspend T.() -> Any) {
    runBlocking { BDDMockito.verify(mock).run { methodCall() } }
}

/**
 * Shorthand to verify that suspend functions were called in a mock according to the [verificationMode],
 * being able to write tests using a similar syntax.
 *
 * verifySuspend(foo, never()) { bar() }
 */
fun <T> verifySuspend(mock: T, verificationMode: VerificationMode, methodCall: suspend T.() -> Any) {
    runBlocking { BDDMockito.verify(mock, verificationMode).run { methodCall() } }
}
