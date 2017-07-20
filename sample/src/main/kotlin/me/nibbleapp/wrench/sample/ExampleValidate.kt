package me.nibbleapp.wrench.sample

import me.nibbleapp.wrench.sample.error.EmailErrors
import me.nibbleapp.wrench.sample.error.SendEmailErrors
import me.nibbleapp.wrench.sample.executor.MainExecutor
import me.nibbleapp.wrench.sample.usecase.email.sendEmail
import me.nibbleapp.wrench.sample.usecase.email.validateEmail
import me.nibbleapp.wrench.type.Validation
import me.nibbleapp.wrench.usecase.UseCase
import me.nibbleapp.wrench.usecase.Validate
import java.util.concurrent.Executors


fun main(args: Array<String>) {

    val recipients = emailsInvalid


    Validate(Validation(
            recipients.map { validateEmail(it).invoke() }, // It could be a list of Either (small validations)
            UseCase(sendEmail(recipients), useCaseExecutor))
    ).validate(
            onValidationError(),
            handleValidationErrors(),
            onValidationSuccess()
    ).map {
        it.execute(onSendError(), onSendSuccess())
    }
}

private fun handleValidationErrors(): (EmailErrors) -> Unit = {
    when (it) {
        is EmailErrors.Empty -> println("\t\"${it.email}\" is empty")
        is EmailErrors.Invalid -> println("\t\"${it.email}\" is invalid")
        is EmailErrors.TooLong -> println("\t\"${it.email}\" is too long")
        is EmailErrors.TooShort -> println("\t\"${it.email}\" is too short")
    }
}

private fun onValidationError(): () -> Unit = {
    println("Validation wasn't success")
}

fun onValidationSuccess(): () -> Unit = {
    println("Validation was success")
}

private fun onSendError(): (SendEmailErrors) -> Unit = {
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

