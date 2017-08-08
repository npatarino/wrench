package me.nibbleapp.wrench.usecase

import me.nibbleapp.wrench.type.Either
import me.nibbleapp.wrench.type.Validation

class Validate<out ValidationError : Any, Error, Result>(list: List<() -> Validation<ValidationError>>,
                                                                 private val useCase: UseCase<Error, Result>) {

    fun validate(): Either<List<ValidationError>, UseCase<Error, Result>> =
            if (hasFailures) Either.Left(failures) else Either.Right(useCase)

    private val failures: List<ValidationError> = list
            .map {
                val validation = it.invoke()
                when (validation) {
                    is Validation.Invalid -> validation.error
                    is Validation.Valid -> null
                }
            }.filterNotNull()

    private val hasFailures: Boolean = failures.isNotEmpty()

}