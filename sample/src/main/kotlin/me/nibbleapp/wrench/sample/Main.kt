package me.nibbleapp.wrench.sample

import me.nibbleapp.wrench.sample.error.EmailErrors
import me.nibbleapp.wrench.sample.error.SendEmailErrors
import me.nibbleapp.wrench.sample.executor.MainExecutor
import me.nibbleapp.wrench.sample.usecase.EmailValidate
import java.util.concurrent.Executors

val useCaseExecutor = MainExecutor(Executors.newScheduledThreadPool(4))

val emailsInvalid = listOf(
        "npatarino@gmail.com",
        "npatarino",
        "diegoarmandomaradonabestplayerintheuniverse@gmail.com",
        "a@b.cd",
        ""
)

val emailsValid = listOf(
        "npatarino@gmail.com",
        "diegoarmandomaradona@gmail.com",
        "abcdefg@hij.kl"
)

fun main(args: Array<String>) {

    testValidate()

}

private fun testValidate() {
    val validate = EmailValidate(
            emailsValid,
            useCaseExecutor)

    validate.execute(onValidationError(),
            handleValidationErrors(),
            onValidationSuccess(),
            onSendError(),
            onSendSuccess())
}

fun onValidationError(): () -> Unit = {
    println("Validation wasn't success")
}

fun handleValidationErrors(): (EmailErrors) -> Unit = {
    when (it) {
        is EmailErrors.Empty -> println("\t\"${it.email}\" is empty")
        is EmailErrors.Invalid -> println("\t\"${it.email}\" is invalid")
        is EmailErrors.TooLong -> println("\t\"${it.email}\" is too long")
        is EmailErrors.TooShort -> println("\t\"${it.email}\" is too short")
    }
}

fun onValidationSuccess() = {
    println("Validation was success")
}

private fun onSendError(): (SendEmailErrors) -> Unit = {
    println("UseCase error")
}

private fun onSendSuccess(): (Boolean) -> Unit = {
    println("UseCase success")
}


