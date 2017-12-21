package com.idealista.android.wrench.usecase

import com.idealista.android.wrench.executor.UseCaseExecutor
import com.idealista.android.wrench.type.Either
import kotlinx.coroutines.experimental.Job

class UseCaseExecutable<Error, Result> internal constructor(private val function: () -> Either<Error, Result>,
                                                            private val ui: (Either<Error, Result>) -> Unit,
                                                            private val delay: Long) {

    fun run(useCaseExecutor: UseCaseExecutor) = useCaseExecutor.execute(function, ui, delay)

}