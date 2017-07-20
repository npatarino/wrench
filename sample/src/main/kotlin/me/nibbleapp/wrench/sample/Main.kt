package me.nibbleapp.wrench.sample

import me.nibbleapp.wrench.sample.error.EmailErrors
import me.nibbleapp.wrench.sample.error.SendEmailErrors
import me.nibbleapp.wrench.sample.executor.MainExecutor
import me.nibbleapp.wrench.sample.usecase.EmailValidate

val emailsInvalid = listOf(
        "npatarino@gmail.com",
        "npatarino",
        "diegoarmandomaradonabestplayerintheuniverse@gmail.com",
        "a@b.cd"
)

val emailsValid = listOf(
        "npatarino@gmail.com",
        "diegoarmandomaradona@gmail.com",
        "abcdefg@hij.kl"
)

fun main(args: Array<String>) {

    val validate = EmailValidate(
            emailsInvalid,
            MainExecutor())

    validate.execute(onValidationError(), handleValidationErrors(), onValidationSuccess(), onSendError(), onSendSuccess())

}

fun onValidationError(): () -> Unit = {
    println("Validation wasn't success")
}

fun handleValidationErrors(): (EmailErrors) -> Unit = {
    when (it) {
        is EmailErrors.Empty -> println("\tEmail is empty")
        is EmailErrors.Invalid -> println("\tEmail is invalid")
        is EmailErrors.TooShort -> println("\tEmail is too short")
        is EmailErrors.TooLong -> println("\tEmail is too long")
    }
}

fun onValidationSuccess() = {
    println("Works with no errors")
}

private fun onSendError(): (SendEmailErrors) -> Unit = {
    println("Validation doesn't match")
}

private fun onSendSuccess(): (Boolean) -> Unit = {
    println("Works with no errors")
}


