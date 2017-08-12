package me.nibbleapp.wrench.sample

import kotlinx.coroutines.experimental.runBlocking
import me.nibbleapp.wrench.sample.error.EmailError
import me.nibbleapp.wrench.sample.error.SendEmailError
import me.nibbleapp.wrench.sample.executor.DefaultExecutor
import me.nibbleapp.wrench.sample.model.Message
import me.nibbleapp.wrench.sample.usecase.email.sendEmail
import me.nibbleapp.wrench.sample.usecase.email.validateEmail
import me.nibbleapp.wrench.type.Either
import me.nibbleapp.wrench.usecase.UseCase
import me.nibbleapp.wrench.validation.Validate


fun main(args: Array<String>) = runBlocking<Unit> {

    val recipients = listOf("npatarino@gmail.com", "npatarino@idealista.com")

    val useCase = UseCase<SendEmailError, Message>()
            .bg(sendEmail(recipients))
            .then { it.map { Message(it.text.reversed(), it.date) } }
            .then { it.map { Message(it.text.toUpperCase(), it.date) } }
            .map { it.toModel() }
            .ui({ handleResult(it) })

    val deferred = Validate<EmailError, String>()
            .add { validateEmail("npatarino@gmail.com") }
            .add { validateEmail("npatarino@idealista.com") }
            .with(useCase)
            .invalids { handleInvalidate(it) }
            .valid { println("Valid") }
            .run(DefaultExecutor())
    deferred?.join()
}

private fun handleResult(model: Either<SendEmailError, String>) {
    model.fold(
            { handleSendEmailErrors(it) },
            { onSendEmailSuccess(it) }
    )
}

private fun handleSendEmailErrors(sendEmailError: SendEmailError) {
    when (sendEmailError) {
        is SendEmailError.ServerError -> println("Server error")
        is SendEmailError.NoNetworkError -> println("No network error")
        is SendEmailError.BadRequest -> println("Bad request error")
    }
}

private fun onSendEmailSuccess(message: String) {
    println("It worked ($message)")
}

private fun handleInvalidate(it: EmailError): Unit = when (it) {
    is EmailError.Empty -> println("\t\"${it.email}\" is empty")
    is EmailError.Invalid -> println("\t\"${it.email}\" is invalid")
    is EmailError.TooLong -> println("\t\"${it.email}\" is too long")
    is EmailError.TooShort -> println("\t\"${it.email}\" is too short")
}

