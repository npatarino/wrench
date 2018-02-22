package com.idealista.android.wrench.usecase

import com.idealista.android.wrench.executor.UseCaseExecutor
import com.idealista.android.wrench.type.Either

class Then<Error, Result> internal constructor(private val function: () -> Either<Error, Result>,
                                               private val delay: Long = 0) {

    infix fun <Mapped> then(f: (Either<Error, Result>) -> Either<Error, Mapped>): Then<Error, Mapped> =
            Then({ f(function.invoke()) }, delay)


    infix fun <Mapped> map(f: (Result) -> Mapped): Then<Error, Mapped> =
            Then({ function.invoke().map { f(it) } }, delay)


    fun ui(ui: (Either<Error, Result>) -> Unit = {}): UseCaseExecutable<Error, Result> =
            UseCaseExecutable(function, ui, delay)

    fun run(useCaseExecutor: UseCaseExecutor) =
            useCaseExecutor.execute(function, {}, delay)


}