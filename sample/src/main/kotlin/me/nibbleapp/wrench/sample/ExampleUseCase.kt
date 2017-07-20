package me.nibbleapp.wrench.sample

import me.nibbleapp.wrench.sample.error.EmailErrors
import me.nibbleapp.wrench.sample.usecase.email.validateEmail
import me.nibbleapp.wrench.usecase.UseCase

fun main(args: Array<String>) {

    UseCase(validateEmail(emailValid),
            useCaseExecutor)
            .execute(handleValidationErrors(), onSendEmailSuccess())

}

private fun handleValidationErrors(): (EmailErrors) -> Unit = {
    when (it) {
        is EmailErrors.Empty -> println("\t\"${it.email}\" is empty")
        is EmailErrors.Invalid -> println("\t\"${it.email}\" is invalid")
        is EmailErrors.TooLong -> println("\t\"${it.email}\" is too long")
        is EmailErrors.TooShort -> println("\t\"${it.email}\" is too short")
    }
}

private fun onSendEmailSuccess(): (Boolean) -> Unit = { println("It worked ($it)") }

val emailValid = "npatarino@gmail.com"
val emailInvalid = "npatarino@gmail"