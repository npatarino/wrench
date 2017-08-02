package me.nibbleapp.wrench.sample.usecase.email

import me.nibbleapp.wrench.sample.error.SendEmailError
import me.nibbleapp.wrench.type.Either

fun sendEmail(recipients: List<String>): () -> Either<SendEmailError, Boolean> = {
    Either.Left(SendEmailError.NoNetworkError())
}