package me.nibbleapp.wrench.sample

import me.nibbleapp.wrench.sample.error.SendEmailError
import me.nibbleapp.wrench.sample.executor.DefaultExecutor
import me.nibbleapp.wrench.sample.model.Message
import me.nibbleapp.wrench.sample.usecase.email.sendEmail
import me.nibbleapp.wrench.type.Either
import me.nibbleapp.wrench.usecase.UseCase

fun main(args: Array<String>) {

    val recipients = listOf("npatarino@gmail.com", "npatarino@idealista.com")
    val delay: Long = 5000
    val sleep: Long = 8000

    val completion = UseCase<SendEmailError, Message>()
            .bg(sendEmail(recipients), delay)
            .and { it.map { Message(it.text.reversed(), it.date) } }
            .and { it.map { Message(it.text.toUpperCase(), it.date) } }
            .map { it.toModel() }

    val result = completion.run({ ui(it) }, DefaultExecutor())

    Thread.sleep(sleep)

    if (result.isCompleted) {
        println("Late")
    } else {
        println("Cancel")
        result.cancel()
    }

    println("Finished")

}

private fun ui(model: Either<SendEmailError, String>) {
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

fun Message.toModel() = text
