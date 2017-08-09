package me.nibbleapp.wrench.sample

import kotlinx.coroutines.experimental.runBlocking
import me.nibbleapp.wrench.sample.error.SendEmailError
import me.nibbleapp.wrench.sample.executor.DefaultExecutor
import me.nibbleapp.wrench.sample.model.Message
import me.nibbleapp.wrench.sample.usecase.email.sendEmail
import me.nibbleapp.wrench.type.Either
import me.nibbleapp.wrench.usecase.UseCase

fun main(args: Array<String>) = runBlocking {

    val recipients = listOf("npatarino@gmail.com", "npatarino@idealista.com")
    val delay: Long = 5000
    val sleep: Long = 2000

//    example1(recipients, delay, sleep)
    example2(recipients)
//    example3(recipients)
//    example4(recipients)

}

private fun example1(recipients: List<String>, delay: Long, sleep: Long) = runBlocking {
    val useCase = UseCase<SendEmailError, Message>()
            .bg(sendEmail(recipients), delay)
            .and { reverseMessage(it) }
            .and { upperCaseMessage(it) }
            .map { it.toModel() }
            .ui({ handleResult(it) })
            .run(DefaultExecutor())

    Thread.sleep(sleep)

    if (useCase.isCompleted) {
        println("Late to cancel")
    } else {
        println("Cancel")
        useCase.cancel()
    }

    useCase.join()
}

private fun example2(recipients: List<String>) = runBlocking {
    val useCase = UseCase<SendEmailError, Message>()
            .bg(sendEmail(recipients))
            .and { reverseMessage(it) }
            .and { upperCaseMessage(it) }
            .map { it.toModel() }
            .ui({ handleResult(it) })
            .run(DefaultExecutor())
    useCase.join()

}

private fun example3(recipients: List<String>) = runBlocking {
    val useCase = UseCase<SendEmailError, Message>()
            .bg(sendEmail(recipients))
            .run(DefaultExecutor())
    useCase.join()
}

private fun example4(recipients: List<String>) = runBlocking {
    val useCase = UseCase<SendEmailError, Message>()
            .bg(sendEmail(recipients))
            .map { it.toModel() }
            .ui({ handleResult(it) })
            .run(DefaultExecutor())
    useCase.join()
}


private fun upperCaseMessage(it: Either<SendEmailError, Message>) = it.map { Message(it.text.toUpperCase(), it.date) }

private fun reverseMessage(it: Either<SendEmailError, Message>) = it.map { Message(it.text.reversed(), it.date) }

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

fun Message.toModel(): String = text
