package com.idealista.android.wrench.executor.coroutine.job

import com.idealista.android.wrench.executor.CancellationToken
import kotlinx.coroutines.experimental.Job

class JobCancellationToken(private val job: Job) : CancellationToken {

    override fun cancel(message: String): Boolean = job.cancel()
}