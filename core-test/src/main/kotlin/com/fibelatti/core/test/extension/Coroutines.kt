package com.fibelatti.core.test.extension

import kotlinx.coroutines.runBlocking
import org.mockito.BDDMockito
import org.mockito.verification.VerificationMode

fun <T> givenSuspend(methodCall: suspend () -> T): BDDMockito.BDDMyOngoingStubbing<T> =
    BDDMockito.given(runBlocking { methodCall() })

fun <T> callSuspend(methodCall: suspend () -> T): T = runBlocking { methodCall() }

fun <T> verifySuspend(mock: T, methodCall: suspend T.() -> Any) {
    runBlocking { BDDMockito.verify(mock).run { methodCall() } }
}

fun <T> verifySuspend(mock: T, verificationMode: VerificationMode, methodCall: suspend T.() -> Any) {
    runBlocking { BDDMockito.verify(mock, verificationMode).run { methodCall() } }
}
