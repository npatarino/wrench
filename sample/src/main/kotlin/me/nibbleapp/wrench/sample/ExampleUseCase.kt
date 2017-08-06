package me.nibbleapp.wrench.sample

import me.nibbleapp.wrench.sample.error.EmailError
import me.nibbleapp.wrench.sample.error.SendEmailError
import me.nibbleapp.wrench.sample.usecase.email.checkEmail
import me.nibbleapp.wrench.sample.usecase.email.sendEmail
import me.nibbleapp.wrench.usecase.UseCase

fun main(args: Array<String>) {

    UseCase(sendEmail(listOf("", "", "")), useCaseExecutor).execute(10000).get().fold(handleSendEmailErrors(), onSendEmailSuccess())

    UseCase(checkEmail(emailValid), useCaseExecutor).execute().fold(handleValidationErrors(), onSendEmailSuccess())
}

fun handleValidationErrors(): (EmailError) -> Unit = {
    when (it) {
        is EmailError.Empty -> println("\t\"${it.email}\" is empty")
        is EmailError.Invalid -> println("\t\"${it.email}\" is invalid")
        is EmailError.TooLong -> println("\t\"${it.email}\" is too long")
        is EmailError.TooShort -> println("\t\"${it.email}\" is too short")
    }
}

private fun handleSendEmailErrors(): (SendEmailError) -> Unit = {
    when (it) {
        is SendEmailError.ServerError -> println("Server error")
        is SendEmailError.NoNetworkError -> println("No network error")
        is SendEmailError.BadRequest -> println("Bad request error")
    }
}

private fun onSendEmailSuccess(): (Boolean) -> Unit = { println("It worked ($it)") }

val emailValid = "npatarino@gmail.com"
val emailInvalid = "n@n.c"