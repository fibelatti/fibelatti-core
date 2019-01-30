package com.fibelatti.core.functional

/**
 * Handy class to define single responsibility use cases with parameters, that can be invoked as a function thanks to
 * operator overloading.
 *
 * class Foo : UseCaseWithParams<Bar, Foo.Params>
 *
 * val result: Bar = foo(Foo.Params(baz))
 */
abstract class UseCaseWithParams<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Result<Type>

    suspend operator fun invoke(params: Params): Result<Type> = run(params)
}

/**
 * Handy class to define single responsibility use cases, that can be invoked as a function thanks to operator
 * overloading.
 *
 * class Foo : UseCase<Bar>
 *
 * val result: Bar = foo()
 */
abstract class UseCase<out Type> where Type : Any {

    abstract suspend fun run(): Result<Type>

    suspend operator fun invoke(): Result<Type> = run()
}
