package com.fibelatti.core.rx.functional

abstract class UseCase<out Type, in Params> where Type : Any {
    abstract fun run(params: Params): Type

    operator fun invoke(params: Params): Type = run(params)

    class None
}
