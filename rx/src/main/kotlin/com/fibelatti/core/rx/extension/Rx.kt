package com.fibelatti.core.rx.extension

import io.reactivex.Completable
import io.reactivex.CompletableEmitter
import io.reactivex.MaybeEmitter
import io.reactivex.Notification
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Single
import io.reactivex.SingleEmitter

// region Single
@Suppress("CheckResult")
fun <T> Single<T>.subscribeIgnoringResult() {
    subscribe({}, {})
}

fun <T> Single<T>.flatMapIf(condition: Boolean, mapper: (T) -> Single<T>): Single<T> =
    if (condition) flatMap { mapper(it) } else this
// endregion

// region Completable
@Suppress("CheckResult")
fun Completable.subscribeIgnoringResult() {
    subscribe({}, {})
}

fun <T> Completable.emitOnComplete(itemToEmit: T, exceptionToThrow: Throwable? = null): Single<T> =
    Single.create { emitter ->
        subscribe(
            { emitter.ifNotDisposed { onSuccess(itemToEmit) } },
            { error -> emitter.ifNotDisposed { onError(exceptionToThrow ?: error) } }
        )
    }

fun <T> Completable.emitErrorOnComplete(exceptionToThrow: Throwable): Single<T> =
    Single.create { emitter ->
        subscribe(
            { emitter.ifNotDisposed { onError(exceptionToThrow) } },
            { emitter.ifNotDisposed { onError(exceptionToThrow) } }
        )
    }

fun <T> Completable.emitFinally(itemToEmit: T): Single<T> =
    Single.create { emitter ->
        subscribe(
            { emitter.ifNotDisposed { onSuccess(itemToEmit) } },
            { emitter.ifNotDisposed { onSuccess(itemToEmit) } }
        )
    }

fun Completable.ifCompletes(chainableCompletableInvocation: () -> Completable): Completable =
    Completable.create { emitter ->
        subscribe(
            {
                chainableCompletableInvocation().subscribe(
                    { emitter.ifNotDisposed { onComplete() } },
                    { error -> emitter.ifNotDisposed { onError(error) } }
                )
            },
            { error -> emitter.ifNotDisposed { onError(error) } }
        )
    }
// endregion

// region Observable
@Suppress("CheckResult")
fun <T> Observable<T>.subscribeIgnoringResult() {
    subscribe({}, {})
}

inline fun <T> Observable<T>.filterNotifications(crossinline predicate: (Notification<T>) -> Boolean): Observable<T> =
    materialize().filter { predicate(it) }.dematerialize { it }
// endregion

// region Emitters
inline fun <T> MaybeEmitter<T>.ifNotDisposed(body: MaybeEmitter<T>.() -> Unit) {
    if (!isDisposed) body()
}

inline fun <T> SingleEmitter<T>.ifNotDisposed(body: SingleEmitter<T>.() -> Unit) {
    if (!isDisposed) body()
}

inline fun CompletableEmitter.ifNotDisposed(body: CompletableEmitter.() -> Unit) {
    if (!isDisposed) body()
}

inline fun <T> ObservableEmitter<T>.ifNotDisposed(body: ObservableEmitter<T>.() -> Unit) {
    if (!isDisposed) body()
}
// endregion
