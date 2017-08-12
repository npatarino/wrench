package me.nibbleapp.wrench.validation

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