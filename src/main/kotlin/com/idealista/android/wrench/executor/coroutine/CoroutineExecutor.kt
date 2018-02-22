package com.idealista.android.wrench.executor.coroutine

import com.idealista.android.wrench.executor.CancellationToken
import com.idealista.android.wrench.executor.UseCaseExecutor
import com.idealista.android.wrench.executor.coroutine.job.JobCancellationToken
import com.idealista.android.wrench.type.Either
import kotlinx.coroutines.experimental.*

class CoroutineExecutor : UseCaseExecutor {

    override fun <Error, Result> execute(background: () -> Either<Error, Result>,
                                         ui: (Either<Error, Result>) -> Unit,
                                         delayed: Long): CancellationToken = JobCancellationToken(launch {
        delay(delayed)
        ui(background())
    })

}