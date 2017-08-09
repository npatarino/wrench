package me.nibbleapp.wrench.validation

import kotlinx.coroutines.experimental.Deferred
import me.nibbleapp.wrench.executor.UseCaseExecutor
import me.nibbleapp.wrench.type.Either
import me.nibbleapp.wrench.usecase.UseCaseExecutable

class Validate<Error, T>(private val validations: MutableList<() -> Either<Error, T>> = mutableListOf()) {

    fun add(validation: () -> Either<Error, T>): Validate<Error, T> {
        validations.add(validation)
        return Validate(validations)
    }

    fun <UseCaseError, UseCaseResult> with(useCaseExecutable: UseCaseExecutable<UseCaseError, UseCaseResult>) =
            ValidateExecutable(
                    validations = validations,
                    useCaseExecutable = useCaseExecutable)

}

class ValidateExecutable<Error, T, UseCaseError, UseCaseResult>(
        private val validations: MutableList<() -> Either<Error, T>>,
        private val onInvalidFunction: (Error) -> Unit = {},
        private val onValidFunction: () -> Unit = {},
        private val useCaseExecutable: UseCaseExecutable<UseCaseError, UseCaseResult>) {

    fun invalids(onInvalid: (Error) -> Unit): ValidateExecutable<Error, T, UseCaseError, UseCaseResult> =
            ValidateExecutable(validations, onInvalid, onValidFunction, useCaseExecutable)

    fun valid(onValid: () -> Unit): ValidateExecutable<Error, T, UseCaseError, UseCaseResult> =
            ValidateExecutable(validations, onInvalidFunction, onValid, useCaseExecutable)

    fun run(executor: UseCaseExecutor<UseCaseError, UseCaseResult>): Deferred<Unit>? {

        val hasFailures = validations.map {
            it()
        }.map {
            it.swap().map(onInvalidFunction).swap()
        }.filter {
            it.isLeft()
        }.isNotEmpty()

        if (hasFailures) return null

        onValidFunction()
        return useCaseExecutable.run(executor)
    }
}
