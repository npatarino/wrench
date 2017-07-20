package me.nibbleapp.wrench.usecase

import me.nibbleapp.wrench.type.Validation


abstract class Validate<out ValidationError : Any, Error, Result> {

    abstract val getValidation: Validation<ValidationError>

    abstract val useCase: UseCase<Error, Result>

    fun execute(onValidationError: (ValidationError) -> Unit,
                onValidationSuccess: () -> Unit,
                onError: (Error) -> Unit,
                onSuccess: (Result) -> Unit): Unit {
        getValidation.validate(
                onValidationSuccess,
                onValidationError,
                onValidationSuccess(onError, onSuccess)
        )
    }

    private fun onValidationSuccess(onError: (Error) -> Unit, onSuccess: (Result) -> Unit) =
            { useCase.execute(onError, onSuccess) }

}