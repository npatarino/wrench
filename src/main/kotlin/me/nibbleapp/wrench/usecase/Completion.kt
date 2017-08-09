package me.nibbleapp.wrench.usecase

import kotlinx.coroutines.experimental.Deferred
import me.nibbleapp.wrench.executor.UseCaseExecutor
import me.nibbleapp.wrench.type.Either

class Completion<Error, Result>(private val function: () -> Either<Error, Result>,
                                private val delay: Long = 0) {

    infix fun <Mapped> and(f: (Either<Error, Result>) -> Either<Error, Mapped>): Completion<Error, Mapped> {
        return Completion({ f(function.invoke()) }, delay)
    }

    infix fun <Mapped> map(f: (Result) -> Mapped): Completion<Error, Mapped> {
        return Completion({ function.invoke().map { f(it) } }, delay)
    }

    fun get(ui: (Either<Error, Result>) -> Unit = {},
            useCaseExecutor: UseCaseExecutor<Error, Result>): Deferred<Unit> =
            useCaseExecutor.execute(function, ui, delay)

}