package me.nibbleapp.wrench.usecase

import me.nibbleapp.wrench.executor.UseCaseExecutor
import me.nibbleapp.wrench.type.Either
import java.util.concurrent.Future

class UseCaseExecutable<Error, Result> internal constructor(private val function: () -> Either<Error, Result>,
                                                            private val ui: (Either<Error, Result>) -> Unit,
                                                            private val delay: Long) {

    fun run(useCaseExecutor: UseCaseExecutor): Future<Unit> =
            useCaseExecutor.execute(function, ui, delay)


}