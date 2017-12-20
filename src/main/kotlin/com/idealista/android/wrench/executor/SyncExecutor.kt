package com.idealista.android.wrench.executor

import com.idealista.android.wrench.type.Either
import kotlinx.coroutines.experimental.*
import java.util.concurrent.ScheduledExecutorService

class SyncExecutor : UseCaseExecutor {

    private lateinit var job: Job

    override fun <Error, Result> execute(background: () -> Either<Error, Result>,
                                         ui: (Either<Error, Result>) -> Unit,
                                         delayed: Long): Job {
        runBlocking {
            job = launch {
                delay(delayed)
                ui(background())
            }
            job.join()
        }
        return job
    }

}