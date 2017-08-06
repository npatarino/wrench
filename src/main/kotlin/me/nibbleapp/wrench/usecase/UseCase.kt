package me.nibbleapp.wrench.usecase

import me.nibbleapp.wrench.executor.UseCaseExecutor
import me.nibbleapp.wrench.type.Either
import java.util.concurrent.Future
import java.util.concurrent.ScheduledFuture

class UseCase<Error, Result>(private val background: () -> Either<Error, Result>, private val executor: UseCaseExecutor) {

    private lateinit var future: Future<Either<Error, Result>>

    fun execute(delay: Long = 0) : ScheduledFuture<Either<Error, Result>> {
        future = executor.execute({ background() }, delay)
        return future as ScheduledFuture<Either<Error, Result>>
    }

    fun execute(): Either<Error, Result> {
        future = executor.execute({background()})
        return future.get()
    }

    fun cancel() {
        future.cancel(true)
    }

    fun foreground(it: Either<Error, Result>, error: (Error) -> Unit, success: (Result) -> Unit) {
        it.fold(error, success)
    }
}
