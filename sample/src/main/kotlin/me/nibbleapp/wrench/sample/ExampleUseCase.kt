package me.nibbleapp.wrench.sample

import me.nibbleapp.wrench.sample.error.SendEmailError
import me.nibbleapp.wrench.sample.model.Message
import me.nibbleapp.wrench.sample.usecase.email.sendEmail
import me.nibbleapp.wrench.type.Either
import me.nibbleapp.wrench.usecase.UseCase

fun main(args: Array<String>) {

    val recipients = listOf("npatarino@gmail.com", "npatarino@idealista.com")
    val delay: Long = 5000
    val sleep: Long = 10000

    UseCase<SendEmailError, Message>()
            .bg(sendEmail(recipients), delay)
            .and { it.map { Message(it.text.reversed(), it.date) } }
            .map { it.toModel() }
            .get { ui(it) }

    UseCase<SendEmailError, Message>()
            .bg(sendEmail(recipients), delay)
            .get()

    println("Started")

    Thread.sleep(sleep)
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

private fun onSendEmailSuccess(message: Message) {
    println("It worked ($message)")
}

private fun onSendEmailSuccess(message: String) {
    println("It worked ($message)")
}


private fun Either<SendEmailError, Message>.toModel() = fold(
        { Either.Left(it) },
        { Either.Right(it.toModel()) }
)

fun Message.toModel() = text
