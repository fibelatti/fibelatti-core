package com.fibelatti.core.rx.functional

/**
 * Handy class to define single responsibility use cases with parameters, that can be invoked as a function thanks to
 * operator overloading.
 *
 * class Foo : UseCaseWithParams<Single<Bar>, Foo.Params>
 *
 * foo(Foo.Params(baz)).subscribe()
 */
abstract class UseCaseWithParams<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Type

    suspend operator fun invoke(params: Params): Type = run(params)
}

/**
 * Handy class to define single responsibility use cases, that can be invoked as a function thanks to operator
 * overloading.
 *
 * class Foo : UseCase<Single<Foo>>
 *
 * foo().subscribe()
 */
abstract class UseCase<out Type> where Type : Any {

    abstract fun run(): Type

    operator fun invoke(): Type = run()
}
