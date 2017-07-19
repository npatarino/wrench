package me.nibbleapp.wrench.usecase

import me.nibbleapp.wrench.type.Validation


abstract class Validate<out ValidationError : Any, out Error, out Result>(
        private val useCase: UseCase<Error, Result>) {

    abstract val getValidation: Validation<ValidationError>

    fun execute(onValidationError: (ValidationError) -> Unit,
                onError: (Error) -> Unit,
                onSuccess: (Result) -> Unit): Unit {
        getValidation.validate(
                onValidationError,
                onValidationSuccess(onError, onSuccess)
        )
    }

    private fun onValidationSuccess(onError: (Error) -> Unit, onSuccess: (Result) -> Unit) =
            { useCase.execute(onError, onSuccess) }

}