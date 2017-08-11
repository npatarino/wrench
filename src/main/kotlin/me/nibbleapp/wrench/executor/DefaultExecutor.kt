package me.nibbleapp.wrench.sample.executor

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import me.nibbleapp.wrench.executor.UseCaseExecutor
import me.nibbleapp.wrench.type.Either
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class DefaultExecutor<Error, Result> : UseCaseExecutor<Error, Result> {

    override fun execute(background: () -> Either<Error, Result>,
                         ui: (Either<Error, Result>) -> Unit,
                         delayed: Long): Deferred<Unit> =
            async(CommonPool) {
                delay(delayed)
                ui(background())
            }


}