package com.fibelatti.core.functional

import androidx.lifecycle.LiveData

/**
 * Handy class to define single responsibility use cases that return LiveData<Result<T>>. It can be invoked as a
 * function thanks to operator overloading.
 *
 * class Foo : LiveDataUseCase<Bar>
 *
 * val result: LiveData<Result<Bar>> = foo()
 */
abstract class LiveDataUseCase<Type> where Type : Any? {

    abstract suspend fun run(): LiveData<Result<Type>>

    suspend operator fun invoke(): LiveData<Result<Type>> = run()
}

/**
 * Handy class to define single responsibility use cases that return LiveData<Result<T>> and require parameters. It can
 * be invoked as a function thanks to operator overloading.
 *
 * class Foo : LiveDataUseCaseWithParams<Bar, Foo.Params>
 *
 * val result: LiveData<Result<Bar>> = foo(Foo.Params(baz))
 */
abstract class LiveDataUseCaseWithParams<Type, in Params> where Type : Any? {

    abstract suspend fun run(params: Params): LiveData<Result<Type>>

    suspend operator fun invoke(params: Params): LiveData<Result<Type>> = run(params)
}
