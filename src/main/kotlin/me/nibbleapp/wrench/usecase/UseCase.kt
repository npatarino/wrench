package me.nibbleapp.wrench.usecase

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import me.nibbleapp.wrench.type.Either

class UseCase<Error, Result> {

    fun bg(background: () -> Either<Error, Result>, delay: Long = 0): Completion<Error, Result> =
            Completion(background, delay)

}

class Completion<Error, Result>(private val function: () -> Either<Error, Result>,
                                private val delay: Long = 0) {

    infix fun <Mapped> and(f: (Either<Error, Result>) -> Either<Error, Mapped>): Completion<Error, Mapped> {
        return Completion({ f(function.invoke()) }, delay)
    }

    infix fun <Mapped> map(f: (Result) -> Mapped): Completion<Error, Mapped> {
        return Completion({ function.invoke().map { f(it) } }, delay)
    }

    fun get(ui: (Either<Error, Result>) -> Unit): Unit {
        async(CommonPool) {
            delay(delay)
            ui(function())
        }
    }

    fun get() {
        get { }
    }

}

