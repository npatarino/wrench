package me.nibbleapp.wrench.sample

import me.nibbleapp.wrench.sample.error.EmailError
import me.nibbleapp.wrench.sample.executor.DefaultExecutor
import java.util.concurrent.Executors


fun main(args: Array<String>) {

    val recipients = emailsInvalid

//    val useCase = UseCase(sendEmail(recipients), useCaseExecutor)
//
//    val eitherList = recipients.map { validateEmail(it) }
//
//    Validate(eitherList, useCase).validate()
//            .fold(
//                    { handleInvalidate(it) },
//                    {
//                        it.execute().get()
//                                .fold(
//                                        { handleUseCaseError() },
//                                        { handleUseCaseSuccess() }
//                                )
//                    }
//            )

    println("Start")



// Start a coroutine
//    launch(CommonPool) {
//        delay(1000)
//        println("Hello")
//    }

    Thread.sleep(2000) // wait for 2 seconds
    println("Stop")

}

private fun handleUseCaseSuccess() {
    println("Success")
}

private fun handleUseCaseError() {
    println("Something went wrong")
}

private fun handleInvalidate(it: List<EmailError>): List<Unit> = it.map {
    when (it) {
        is EmailError.Empty -> println("\t\"${it.email}\" is empty")
        is EmailError.Invalid -> println("\t\"${it.email}\" is invalid")
        is EmailError.TooLong -> println("\t\"${it.email}\" is too long")
        is EmailError.TooShort -> println("\t\"${it.email}\" is too short")
    }
}


val emailsInvalid = listOf(
        "npatarino@gmail.com",
        "npatarino",
        "diegoarmandomaradonaisthebestplayerintheuniverse@gmail.com",
        "a@b.cd",
        "")

val emailsValid = listOf("npatarino@gmail.com", "diegoarmandomaradona@gmail.com", "abcdefg@hij.kl")

