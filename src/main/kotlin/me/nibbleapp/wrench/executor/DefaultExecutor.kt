package me.nibbleapp.wrench.sample.executor

import me.nibbleapp.wrench.executor.UseCaseExecutor
import java.util.concurrent.*

class DefaultExecutor(val executorService: ScheduledExecutorService) : UseCaseExecutor {

    override fun <T> execute(background: () -> T, delay: Long): ScheduledFuture<T> {
        return executorService.schedule(background, delay, TimeUnit.MILLISECONDS)
    }
}