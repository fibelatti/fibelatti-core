package com.fibelatti.core.functional

abstract class UseCaseWithParams<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Result<Type>

    suspend operator fun invoke(params: Params): Result<Type> = run(params)
}

abstract class UseCase<out Type> where Type : Any {

    abstract suspend fun run(): Result<Type>

    suspend operator fun invoke(): Result<Type> = run()
}
