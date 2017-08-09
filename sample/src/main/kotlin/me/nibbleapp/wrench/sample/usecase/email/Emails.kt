package me.nibbleapp.wrench.sample.usecase.email

import me.nibbleapp.wrench.sample.error.EmailError
import me.nibbleapp.wrench.sample.error.SendEmailError
import me.nibbleapp.wrench.sample.model.Message
import me.nibbleapp.wrench.type.Either
import java.util.*


private val validEmailPattern: Regex = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$".toRegex()
private val maxLengthCharacters = 50
private val minLengthCharacters = 6

private fun isInvalid(email: String) = !validEmailPattern.matches(email)
private fun isTooShort(email: String) = email.length <= minLengthCharacters
private fun isTooLong(email: String) = email.length > maxLengthCharacters


fun sendEmail(recipients: List<String>): () -> Either<SendEmailError, Message> = {
    println("Sending email")
    Either.Right(Message("Hola", Date().time))
}

fun validateEmail(email: String): Either<EmailError, String> = when {
    email.isBlank() -> Either.Left(EmailError.Empty(email))
    isTooLong(email) -> Either.Left(EmailError.TooLong(email))
    isTooShort(email) -> Either.Left(EmailError.TooShort(email))
    isInvalid(email) -> Either.Left(EmailError.Invalid(email))
    else -> Either.Right(email)
}