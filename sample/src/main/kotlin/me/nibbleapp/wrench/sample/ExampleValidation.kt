package me.nibbleapp.wrench.sample

import me.nibbleapp.wrench.sample.validation.validateName
import me.nibbleapp.wrench.sample.validation.validatePassword

fun main(args: Array<String>) {

    val user = User("ouaiwheuiopaheupaSHEUOIPASHEUoipasheuiphasipehusaeuip", "")

    user.let(validateName + validatePassword).fold({
        it.map { println(it) }
    }, {
        println(it)
    })


}