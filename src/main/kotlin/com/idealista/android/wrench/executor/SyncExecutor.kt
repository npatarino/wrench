package com.idealista.android.wrench.executor

import com.idealista.android.wrench.type.Either
import kotlinx.coroutines.experimental.*
import java.util.concurrent.ScheduledExecutorService

class SyncExecutor : UseCaseExecutor {

    override fun <Error, Result> execute(background: () -> Either<Error, Result>,
                                         ui: (Either<Error, Result>) -> Unit,
                                         delayed: Long): Job = runBlocking {
        val job = launch {
            delay(delayed)
            ui(background())
        }
        job.join()
        job
    }

}