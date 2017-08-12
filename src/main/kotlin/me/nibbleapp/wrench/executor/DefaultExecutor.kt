package me.nibbleapp.wrench.sample.executor

import me.nibbleapp.wrench.executor.UseCaseExecutor
import me.nibbleapp.wrench.type.Either
import java.util.concurrent.Future
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class DefaultExecutor(val executorService: ScheduledExecutorService) : UseCaseExecutor {

    override fun <Error, Result> execute(background: () -> Either<Error, Result>,
                                         ui: (Either<Error, Result>) -> Unit,
                                         delayed: Long): Future<Unit> =
            executorService.schedule<Unit>({ ui(background()) }, delayed, TimeUnit.MILLISECONDS)

}