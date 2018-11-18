package com.fibelatti.core.functional

import com.fibelatti.core.functional.Either.Left
import com.fibelatti.core.functional.Either.Right

/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Either] are either an instance of [Left] or [Right].
 * FP Convention dictates that [Left] is used for "failure"
 * and [Right] is used for "success".
 *
 * @see Left
 * @see Right
 */
sealed class Either<out L, out R> {
    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun either(fnL: (L) -> Unit, fnR: (R) -> Unit): Any = when (this) {
        is Left -> fnL(a)
        is Right -> fnR(b)
    }

    fun leftOrNull(): L? = when (this) {
        is Left -> a
        is Right -> null
    }

    fun rightOrNull(): R? = when (this) {
        is Left -> null
        is Right -> b
    }

    companion object {
        @JvmStatic
        fun <L> left(a: L) = Either.Left(a)

        @JvmStatic
        fun <R> right(b: R) = Either.Right(b)
    }

    /**
     * Represents the left side of [Either] class which by convention is a "Failure".
     */
    data class Left<out L>(val a: L) : Either<L, Nothing>()

    /**
     * Represents the right side of [Either] class which by convention is a "Success".
     */
    data class Right<out R>(val b: R) : Either<Nothing, R>()
}

// region Result
typealias Result<T> = Either<Throwable, T>

typealias Success<T> = Either.Right<T>

typealias Failure = Either.Left<Throwable>

val <T> Success<T>.value get() = b

val Failure.error get() = a

val <R> Result<R>.isSuccess get() = isRight

val <R> Result<R>.isFailure get() = isLeft

fun <R> Result<R>.getOrNull() = rightOrNull()

fun <R> Result<R>.exceptionOrNull() = leftOrNull()

fun <R> Result<R>.throwOnFailure() {
    if (this is Failure) throw this.error
}

fun <R> Result<R>.getOrThrow(): R = when (this) {
    is Success -> this.value
    is Failure -> throw this.error
}

fun <R> Result<R>.getOrElse(onFailure: (exception: Throwable) -> R): R = when (this) {
    is Success -> this.value
    is Failure -> onFailure(this.error)
}

fun <R> Result<R>.getOrDefault(defaultValue: R): R = when (this) {
    is Success -> this.value
    is Failure -> defaultValue
}

fun <R> Result<R>.fold(onSuccess: (R) -> R, onFailure: (Throwable) -> R): R = when (this) {
    is Success -> onSuccess(this.value)
    is Failure -> onFailure(this.error)
}

fun <T, R> Result<R>.mapCatching(fn: (R) -> T): Result<T> = when (this) {
    is Success -> catching { fn(this.value) }
    is Failure -> this
}

inline fun <R> catching(block: () -> R): Result<R> = try {
    Success(block())
} catch (exception: Throwable) {
    Failure(exception)
}

inline fun <T> Result<T>.onFailure(action: (exception: Throwable) -> Unit): Result<T> = also {
    it.exceptionOrNull()?.let(action)
}

inline fun <T> Result<T>.onSuccess(action: (value: T) -> Unit): Result<T> = also {
    it.getOrNull()?.let(action)
}
// endregion
