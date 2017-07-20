package me.nibbleapp.wrench.sample.usecase.email

import me.nibbleapp.wrench.sample.error.EmailErrors
import me.nibbleapp.wrench.type.Either

private val validEmailPattern: Regex = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$".toRegex()
private val maxLengthCharacters = 50
private val minLengthCharacters = 6

private fun isInvalid(email: String) = !validEmailPattern.matches(email)
private fun isTooShort(email: String) = email.length <= minLengthCharacters
private fun isTooLong(email: String) = email.length > maxLengthCharacters

fun validateEmail(email: String): () -> Either<EmailErrors, Boolean> = {
    when {
        email.isBlank() -> Either.Left(EmailErrors.Empty(email))
        isTooLong(email) -> Either.Left(EmailErrors.TooLong(email))
        isTooShort(email) -> Either.Left(EmailErrors.TooShort(email))
        isInvalid(email) -> Either.Left(EmailErrors.Invalid(email))
        else -> Either.Right(true)
    }
}