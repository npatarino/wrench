package me.nibbleapp.wrench.executor

import me.nibbleapp.wrench.type.Either
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class DefaultExecutor(private val executorService: ScheduledExecutorService) : UseCaseExecutor {

    override fun <Error, Result> execute(background: () -> Either<Error, Result>,
                                         ui: (Either<Error, Result>) -> Unit,
                                         delayed: Long) {
        executorService.schedule<Unit>({ ui(background()) }, delayed, TimeUnit.MILLISECONDS).get()
    }

}