package me.nibbleapp.wrench.executor

import java.util.concurrent.Future
import java.util.concurrent.ScheduledFuture

interface UseCaseExecutor {

  fun <T> execute(background: () -> T, ui: (result: T) -> Unit): Future<Unit>
  fun <T> execute(background: () -> T, ui: (result: T) -> Unit, delay: Long): ScheduledFuture<*>

}