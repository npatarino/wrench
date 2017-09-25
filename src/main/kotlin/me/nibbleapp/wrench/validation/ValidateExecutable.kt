package me.nibbleapp.wrench.validation

import me.nibbleapp.wrench.executor.UseCaseExecutor
import me.nibbleapp.wrench.type.Either
import me.nibbleapp.wrench.usecase.UseCaseExecutable
import java.util.concurrent.Future

class ValidateExecutable<Error, T, UseCaseError, UseCaseResult>(
        private val validations: MutableList<() -> Either<Error, T>>,
        private val onInvalidFunction: (Error) -> Unit = {},
        private val onValidFunction: () -> Unit = {},
        private val useCaseExecutable: UseCaseExecutable<UseCaseError, UseCaseResult>) {

    fun invalids(onInvalid: (Error) -> Unit): ValidateExecutable<Error, T, UseCaseError, UseCaseResult> =
            ValidateExecutable(validations, onInvalid, onValidFunction, useCaseExecutable)

    fun valid(onValid: () -> Unit): ValidateExecutable<Error, T, UseCaseError, UseCaseResult> =
            ValidateExecutable(validations, onInvalidFunction, onValid, useCaseExecutable)

    fun run(executor: UseCaseExecutor): Unit? {

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
