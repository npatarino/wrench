package me.nibbleapp.wrench.executor

import kotlinx.coroutines.experimental.Deferred
import me.nibbleapp.wrench.type.Either

interface UseCaseExecutor<Error, Result> {

    fun execute(background: () -> Either<Error, Result>, ui: (Either<Error, Result>) -> Unit, delay: Long = 0)
            : Deferred<Unit>

}