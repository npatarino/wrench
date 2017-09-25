package me.nibbleapp.wrench.executor

import me.nibbleapp.wrench.type.Either
import java.util.concurrent.Future

interface UseCaseExecutor {

    fun <Error, Result> execute(background: () -> Either<Error, Result>,
                                ui: (Either<Error, Result>) -> Unit,
                                delayed: Long = 0): Unit

}