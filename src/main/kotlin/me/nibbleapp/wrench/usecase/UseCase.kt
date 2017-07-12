package me.nibbleapp.wrench.usecase

import me.nibbleapp.wrench.executor.UseCaseExecutor
import me.nibbleapp.wrench.type.Either
import java.util.concurrent.Future

abstract class UseCase<Error, Result>(private val executor: UseCaseExecutor) {

  private lateinit var future: Future<*>

  fun execute(error: (Error) -> Unit, success: (Result) -> Unit) {
    future = executor.execute({ background() }, { foreground(it, error, success) })
  }

  fun execute(error: (Error) -> Unit, success: (Result) -> Unit, delay: Long) {
    future = executor.execute({ background() }, { foreground(it, error, success) }, delay)
  }

  fun execute(): Either<Error, Result> = background()

  fun cancel() {
    future.cancel(true)
  }

  fun foreground(it: Either<Error, Result>, error: (Error) -> Unit, success: (Result) -> Unit) {
    it.fold(error, success)
  }

  abstract fun background(): Either<Error, Result>

}
