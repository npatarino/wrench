package me.nibbleapp.wrench.usecase

import me.nibbleapp.wrench.type.Either
import me.nibbleapp.wrench.type.Validation

class Validate<out ValidationError : Any, Error, Result>(
        private val validation: Validation<ValidationError, Error, Result>) {

    fun validate(onValidationError: () -> Unit = {},
                 handleValidationErrors: (ValidationError) -> Unit = {},
                 onValidationSuccess: () -> Unit = {}): Either<List<ValidationError>, UseCase<Error, Result>> =
            validation.execute(onValidationError, handleValidationErrors, onValidationSuccess)

}