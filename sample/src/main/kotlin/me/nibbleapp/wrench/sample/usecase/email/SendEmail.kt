package me.nibbleapp.wrench.sample.usecase.email

import me.nibbleapp.wrench.sample.error.SendEmailError
import me.nibbleapp.wrench.sample.model.Message
import me.nibbleapp.wrench.type.Either
import java.util.*

fun sendEmail(recipients: List<String>): () -> Either<SendEmailError, Message> = {
    println("Sending email")
    Either.Right(Message("Hola", Date().time))
}