package com.idealista.android.wrench.executor

import com.idealista.android.wrench.type.Either
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.ScheduledExecutorService

class DefaultExecutor(private val executorService: ScheduledExecutorService) : UseCaseExecutor {

    private lateinit var job: Job

    override fun <Error, Result> execute(background: () -> Either<Error, Result>,
                                         ui: (Either<Error, Result>) -> Unit,
                                         delayed: Long): Job {
        job = launch {
            delay(delayed)
            ui(background())
        }
        return job
    }

}