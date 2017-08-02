package me.nibbleapp.wrench.usecase

import me.nibbleapp.wrench.type.Either
import me.nibbleapp.wrench.type.Validation

class Validate<out ValidationError : Any, Error, Result>(
        list: List<Validation<ValidationError>>,
        val useCase: UseCase<Error, Result>) {

    fun validate(onValidationError: () -> Unit = {},
                 handleValidationErrors: (ValidationError) -> Unit = {},
                 onValidationSuccess: () -> Unit = {}): Either<List<ValidationError>, UseCase<Error, Result>> =
            execute(onValidationError, handleValidationErrors, onValidationSuccess)


    private val failures: List<ValidationError> = list
            .map {
                when (it) {
                    is Validation.Invalid -> it.error
                    is Validation.Valid -> null
                }
            }.filterNotNull()

    val hasFailures: Boolean = failures.isNotEmpty()

    private fun <F> execute(failure: () -> F,
                    handleValidationErrors: (ValidationError) -> Unit,
                    validationSuccess: () -> Unit): Either<List<ValidationError>, UseCase<Error, Result>> {
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