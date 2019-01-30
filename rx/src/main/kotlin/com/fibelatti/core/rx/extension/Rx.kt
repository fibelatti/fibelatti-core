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
/**
 * Calls subscribe on `this`, with an empty function for both onSuccess and onError.
 */
@Suppress("CheckResult")
fun <T> Single<T>.subscribeIgnoringResult() {
    subscribe({}, {})
}

/**
 * Chains a flapMap to `this` if [predicate] is true, applying [mapper] to the item emitted by the source Single.
 *
 * @param predicate whether or not the mapper function will be applied
 * @param mapper function to transform the emitter item
 *
 * @return the new Single if [predicate] is true, the original Single otherwise
 */
fun <T> Single<T>.flatMapIf(predicate: Boolean, mapper: (T) -> Single<T>): Single<T> =
    if (predicate) flatMap { mapper(it) } else this
// endregion

// region Completable
/**
 * Calls subscribe on `this`, with an empty function for both onComplete and onError.
 */
@Suppress("CheckResult")
fun Completable.subscribeIgnoringResult() {
    subscribe({}, {})
}

/**
 * Creates a Single that will subscribe to `this` and emit after `this` completes.
 *
 * @param item value to be emitted by onSuccess once source Completable calls onComplete
 * @param alternateError optional throwable to be emitted by onError once source Completable call onError. If null then
 * original error is emitted.
 *
 * @return the new Single
 */
fun <T> Completable.emitOnComplete(item: T, alternateError: Throwable? = null): Single<T> =
    Single.create { emitter ->
        subscribe(
            { emitter.ifNotDisposed { onSuccess(item) } },
            { error -> emitter.ifNotDisposed { onError(alternateError ?: error) } }
        )
    }

/**
 * Creates a Single that will subscribe to `this` and always call onError after `this` completes, regardless of
 * onComplete or onError.
 *
 * @param error throwable to be emitted by onError once source Completable calls onComplete or onError
 *
 * @return the new Completable
 */
fun <T> Completable.emitErrorOnComplete(error: Throwable): Single<T> =
    Single.create { emitter ->
        subscribe(
            { emitter.ifNotDisposed { onError(error) } },
            { emitter.ifNotDisposed { onError(error) } }
        )
    }

/**
 * Creates a Single that will subscribe to `this` and always call onSuccess after `this` completes, regardless of
 * onComplete or onError.
 *
 * @param item value to be emitted by onSuccess once source Completable calls onComplete or onError
 *
 * @return the new Completable
 */
fun <T> Completable.emitFinally(item: T): Single<T> =
    Single.create { emitter ->
        subscribe(
            { emitter.ifNotDisposed { onSuccess(item) } },
            { emitter.ifNotDisposed { onSuccess(item) } }
        )
    }

/**
 * Creates a Completable that will subscribe to `this` and once onComplete is called then subscribe will be called on
 * [chainableCompletableInvocation]. The new completable will emit the values emitted by [chainableCompletableInvocation].
 *
 * @param chainableCompletableInvocation Completable source that will only be subscribed to if `this` completes without
 * error
 *
 * @return the new Completable
 */
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
/**
 * Calls subscribe on `this`, with an empty function for onNext and onError
 */
@Suppress("CheckResult")
fun <T> Observable<T>.subscribeIgnoringResult() {
    subscribe({}, {})
}

/**
 * Function to peek emissions and possibly filter them out based on a given [predicate].
 *
 * @param predicate filter function to decide whether a given emission will actually be emitted
 */
inline fun <T> Observable<T>.filterNotifications(crossinline predicate: (Notification<T>) -> Boolean): Observable<T> =
    materialize().filter { predicate(it) }.dematerialize { it }
// endregion

// region Emitters
/**
 * Checks if `this` is already disposed before invoking [body].
 *
 * @param body that will only be invoked if isDisposed is false
 */
inline fun <T> MaybeEmitter<T>.ifNotDisposed(body: MaybeEmitter<T>.() -> Unit) {
    if (!isDisposed) body()
}

/**
 * Checks if `this` is already disposed before invoking [body].
 *
 * @param body that will only be invoked if isDisposed is false
 */
inline fun <T> SingleEmitter<T>.ifNotDisposed(body: SingleEmitter<T>.() -> Unit) {
    if (!isDisposed) body()
}

/**
 * Checks if `this` is already disposed before invoking [body].
 *
 * @param body that will only be invoked if isDisposed is false
 */
inline fun CompletableEmitter.ifNotDisposed(body: CompletableEmitter.() -> Unit) {
    if (!isDisposed) body()
}

/**
 * Checks if `this` is already disposed before invoking [body].
 *
 * @param body that will only be invoked if isDisposed is false
 */
inline fun <T> ObservableEmitter<T>.ifNotDisposed(body: ObservableEmitter<T>.() -> Unit) {
    if (!isDisposed) body()
}
// endregion
