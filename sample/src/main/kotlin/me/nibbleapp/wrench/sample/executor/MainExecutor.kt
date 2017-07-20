package me.nibbleapp.wrench.sample.executor

import me.nibbleapp.wrench.executor.UseCaseExecutor
import java.util.concurrent.*

class MainExecutor(val executorService: ScheduledExecutorService) : UseCaseExecutor {

    override fun <T> execute(background: () -> T, ui: (result: T) -> Unit): Future<Unit> {
        ui(background())
        return FutureTask<Unit>({})
    }

    override fun <T> execute(background: () -> T, ui: (result: T) -> Unit, delay: Long): ScheduledFuture<*> {
        return executorService.schedule({ execute(background, ui) }, delay, TimeUnit.MILLISECONDS)
    }
}