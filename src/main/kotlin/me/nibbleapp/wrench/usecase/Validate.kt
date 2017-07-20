package me.nibbleapp.wrench.usecase

import me.nibbleapp.wrench.type.Validation

class Validate<out ValidationError : Any, out Error, out Result>(
        private val validation: Validation<ValidationError>,
        private val useCase: UseCase<Error, Result>) {

    fun validate(onValidationError: () -> Unit = {},
                handleValidationErrors: (ValidationError) -> Unit = {},
                onValidationSuccess: () -> Unit = {},
                onError: (Error) -> Unit = {},
                onSuccess: (Result) -> Unit = {}): Unit {
        validation.execute(
                onValidationError,
                handleValidationErrors,
                onValidationSuccess,
                onValidationSuccess(onError, onSuccess)
        )
    }

    private fun onValidationSuccess(onError: (Error) -> Unit, onSuccess: (Result) -> Unit) =
            { useCase.execute(onError, onSuccess) }

}