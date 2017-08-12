package me.nibbleapp.wrench.usecase

import kotlinx.coroutines.experimental.Deferred
import me.nibbleapp.wrench.executor.UseCaseExecutor
import me.nibbleapp.wrench.type.Either

class UseCaseExecutable<Error, Result> internal constructor(private val function: () -> Either<Error, Result>,
                                                            private val ui: (Either<Error, Result>) -> Unit,
                                                            private val delay: Long) {

    fun run(useCaseExecutor: UseCaseExecutor): Deferred<Unit> =
            useCaseExecutor.execute(function, ui, delay)


}