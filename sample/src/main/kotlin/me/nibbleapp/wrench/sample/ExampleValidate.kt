package me.nibbleapp.wrench.sample

import me.nibbleapp.wrench.sample.error.EmailError
import me.nibbleapp.wrench.sample.error.SendEmailError
import me.nibbleapp.wrench.sample.executor.MainExecutor
import me.nibbleapp.wrench.sample.usecase.email.sendEmail
import me.nibbleapp.wrench.sample.usecase.email.validateEmail
import me.nibbleapp.wrench.usecase.UseCase
import me.nibbleapp.wrench.usecase.Validate
import java.util.concurrent.Executors


fun main(args: Array<String>) {

    val recipients = emailsInvalid

    val useCase = UseCase(sendEmail(recipients), useCaseExecutor)

    val eitherList = recipients.map { validateEmail(it).invoke() }

    Validate(eitherList, useCase).validate(
            onValidationError(),
            handleSendEmailErrors(),
            onValidationSuccess()
    ).map {
        it.execute(onSendError(), onSendSuccess())
    }

}

private fun handleSendEmailErrors(): (EmailError) -> Unit = {
    when (it) {
        is EmailError.Empty -> println("\t\"${it.email}\" is empty")
        is EmailError.Invalid -> println("\t\"${it.email}\" is invalid")
        is EmailError.TooLong -> println("\t\"${it.email}\" is too long")
        is EmailError.TooShort -> println("\t\"${it.email}\" is too short")
    }
}

private fun onValidationError(): () -> Unit = {
    println("Validation wasn't success")
}

fun onValidationSuccess(): () -> Unit = {
    println("Validation was success")
}

private fun onSendError(): (SendEmailError) -> Unit = {
    println("Emails wasn't sent")
}

private fun onSendSuccess(): (Boolean) -> Unit = {
    println("Emails has been sent")
}

val useCaseExecutor = MainExecutor(Executors.newScheduledThreadPool(4))

val emailsInvalid = listOf(
        "npatarino@gmail.com",
        "npatarino",
        "diegoarmandomaradonaisthebestplayerintheuniverse@gmail.com",
        "a@b.cd",
        "")

val emailsValid = listOf("npatarino@gmail.com", "diegoarmandomaradona@gmail.com", "abcdefg@hij.kl")

