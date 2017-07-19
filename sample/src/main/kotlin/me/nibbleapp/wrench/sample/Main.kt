package me.nibbleapp.wrench.sample

import me.nibbleapp.wrench.type.Either
import me.nibbleapp.wrench.type.Validation


fun main(args: Array<String>) {

    val emailEmpty = Either.Right(true)
    val emailInvalid = Either.Left(Errors.Invalid())
    val emailTooLong = Either.Right(true)
    val emailTooShort = Either.Left(Errors.TooShort())

    val validation = Validation(emailEmpty, emailInvalid, emailTooLong, emailTooShort)


    validation.validate({
        when (it) {
            is Errors.Empty -> println("Is empty")
            is Errors.Invalid -> println("Is invalid")
            is Errors.TooShort -> println("Is too short")
            is Errors.TooLong -> println("Is too long")
        }
    }, {
        println("Works with no errors")
    })


}


