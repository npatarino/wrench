package me.nibbleapp.wrench.type

import me.nibbleapp.wrench.usecase.UseCase

class Validation<out E : Any, Error, Result>(list: List<Either<E, *>>, val useCase: UseCase<Error, Result>) {

    val failures: List<E> = list.filter { it.isLeft() }
            .map { it.swap().getOrNull() }
            .filterNotNull()

    val hasFailures: Boolean = failures.isNotEmpty()

    fun <F> execute(failure: () -> F,
                    handleValidationErrors: (E) -> Unit,
                    validationSuccess: () -> Unit): Either<List<E>, UseCase<Error, Result>> {
        if (hasFailures) {
            failure()
            failures.map {
                handleValidationErrors(it)
            }
            return Either.Left(failures)
        } else {
            validationSuccess()
            return Either.Right(useCase)
        }
    }

}