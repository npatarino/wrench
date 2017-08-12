package me.nibbleapp.wrench.usecase

import me.nibbleapp.wrench.executor.UseCaseExecutor
import me.nibbleapp.wrench.type.Either
import java.util.concurrent.Future

class Then<Error, Result> internal constructor(private val function: () -> Either<Error, Result>,
                                               private val delay: Long = 0) {

    infix fun <Mapped> then(f: (Either<Error, Result>) -> Either<Error, Mapped>): Then<Error, Mapped> =
            Then({ f(function.invoke()) }, delay)


    infix fun <Mapped> map(f: (Result) -> Mapped): Then<Error, Mapped> =
            Then({ function.invoke().map { f(it) } }, delay)


    fun ui(ui: (Either<Error, Result>) -> Unit = {}): UseCaseExecutable<Error, Result> =
            UseCaseExecutable(function, ui, delay)

    fun run(useCaseExecutor: UseCaseExecutor): Future<Unit> =
            useCaseExecutor.execute(function, {}, delay)


}