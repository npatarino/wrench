package com.idealista.android.wrench.executor

import com.idealista.android.wrench.type.Either
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class DefaultExecutor(private val executorService: ScheduledExecutorService) : UseCaseExecutor {

    override fun <Error, Result> execute(background: () -> Either<Error, Result>,
                                         ui: (Either<Error, Result>) -> Unit,
                                         delayed: Long) {
        executorService.schedule<Unit>({ ui(background()) }, delayed, TimeUnit.MILLISECONDS).get()
    }

}