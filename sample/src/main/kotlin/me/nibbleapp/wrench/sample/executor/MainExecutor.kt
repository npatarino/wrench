package me.nibbleapp.wrench.sample.executor

import me.nibbleapp.wrench.executor.UseCaseExecutor
import java.util.concurrent.*

class MainExecutor : UseCaseExecutor {

    override fun <T> execute(background: () -> T, ui: (result: T) -> Unit, delay: Long): ScheduledFuture<*> {
        val newScheduledThreadPool = Executors.newScheduledThreadPool(4)
        return newScheduledThreadPool.schedule({ ui(background()) }, delay, TimeUnit.MILLISECONDS)
    }

    override fun <T> execute(background: () -> T, ui: (result: T) -> Unit): Future<Unit> {
        return FutureTask<Unit>({ ui(background()) })
    }
}