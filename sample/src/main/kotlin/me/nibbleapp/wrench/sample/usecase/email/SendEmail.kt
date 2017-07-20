package me.nibbleapp.wrench.sample.usecase.email

import me.nibbleapp.wrench.sample.error.SendEmailErrors
import me.nibbleapp.wrench.type.Either

fun sendEmail(recipients: List<String>): () -> Either<SendEmailErrors, Boolean> = {
    Either.Right(false)
}