package me.nibbleapp.wrench.sample.usecase.email

import me.nibbleapp.wrench.sample.error.EmailError
import me.nibbleapp.wrench.type.Either
import me.nibbleapp.wrench.type.Validation

private val validEmailPattern: Regex = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$".toRegex()
private val maxLengthCharacters = 50
private val minLengthCharacters = 6

private fun isInvalid(email: String) = !validEmailPattern.matches(email)
private fun isTooShort(email: String) = email.length <= minLengthCharacters
private fun isTooLong(email: String) = email.length > maxLengthCharacters

fun validateEmail(email: String): () -> Validation<EmailError> = {
    when {
        email.isBlank() -> Validation.Invalid(EmailError.Empty(email))
        isTooLong(email) -> Validation.Invalid(EmailError.TooLong(email))
        isTooShort(email) -> Validation.Invalid(EmailError.TooShort(email))
        isInvalid(email) -> Validation.Invalid(EmailError.Invalid(email))
        else -> Validation.Valid
    }
}

fun checkEmail(email: String): () -> Either<EmailError, Boolean> = {
    validateEmail(email).invoke().toEither()
}