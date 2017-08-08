package me.nibbleapp.wrench.sample

import me.nibbleapp.wrench.sample.error.EmailError
import me.nibbleapp.wrench.sample.error.SendEmailError
import me.nibbleapp.wrench.sample.model.Message
import me.nibbleapp.wrench.sample.usecase.email.sendEmail
import me.nibbleapp.wrench.sample.usecase.email.validateEmail
import me.nibbleapp.wrench.usecase.UseCase

fun main(args: Array<String>) {

    UseCase<SendEmailError, Message>(useCaseExecutor)
            .execute(sendEmail(listOf("", "", "")))
            .get()
            .fold(
                    { handleSendEmailErrors(it) },
                    { onSendEmailSuccess(it) }
            )


    UseCase<EmailError, Boolean>(useCaseExecutor)
            .execute({ validateEmail("diego@maradona.com")().toEither() })
}

private fun handleSendEmailErrors(sendEmailError: SendEmailError) {
    when (sendEmailError) {
        is SendEmailError.ServerError -> println("Server error")
        is SendEmailError.NoNetworkError -> println("No network error")
        is SendEmailError.BadRequest -> println("Bad request error")
    }
}

private fun onSendEmailSuccess(message: Message) {
    println("It worked ($message)")
}