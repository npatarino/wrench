package me.nibbleapp.wrench.executor

import java.util.concurrent.ScheduledFuture

interface UseCaseExecutor {

    fun <T> execute(background: () -> T, delay: Long = 0): ScheduledFuture<T>

}