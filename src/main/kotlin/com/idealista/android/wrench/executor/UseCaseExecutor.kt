package com.idealista.android.wrench.executor

import com.idealista.android.wrench.type.Either

interface UseCaseExecutor {

    fun <Error, Result> execute(background: () -> Either<Error, Result>,
                                ui: (Either<Error, Result>) -> Unit,
                                delayed: Long = 0): Unit

}