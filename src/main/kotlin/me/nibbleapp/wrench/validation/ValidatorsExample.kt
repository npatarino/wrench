package me.nibbleapp.wrench.validation

import me.nibbleapp.wrench.validation.Validators.Companion.validateName
import me.nibbleapp.wrench.validation.Validators.Companion.validatePassword

data class User(val name: String, val password: String)

sealed class UserError
data class UserNameError(val nameError: NameError) : UserError()
data class UserPasswordError(val passwordError: PasswordError) : UserError()

sealed class NameError {
    object NameTooShortError : NameError()
    object NameTooLongError : NameError()
    object NameEmptyError : NameError()
}

sealed class PasswordError {
    object PasswordTooShortError : PasswordError()
    object PasswordTooLongError : PasswordError()
    object PasswordEmptyError : PasswordError()
}

typealias Validator<T, R> = (R) -> Validation<T, R>

class Validators {

    companion object {
        fun validateName(user: User): Validations<UserError, User> = when {
            user.name.isEmpty() -> Validation.invalid(UserNameError(NameError.NameEmptyError))
            user.name.length < 4 -> Validation.invalid(UserNameError(NameError.NameTooShortError))
            user.name.length > 30 -> Validation.invalid(UserNameError(NameError.NameTooLongError))
            else -> Validation.Valid(user)
        }

        fun validatePassword(user: User): Validations<UserError, User> = when {
            user.password.isEmpty() -> Validation.invalid(UserPasswordError(PasswordError.PasswordEmptyError))
            user.password.length < 4 -> Validation.invalid(UserPasswordError(PasswordError.PasswordTooShortError))
            user.password.length > 30 -> Validation.invalid(UserPasswordError(PasswordError.PasswordTooLongError))
            else -> Validation.Valid(user)
        }

        val validateName: (User) -> Validations<UserError, User> = {
            when {
                it.name.isEmpty() -> Validation.invalid(UserNameError(NameError.NameEmptyError))
                it.name.length < 4 -> Validation.invalid(UserNameError(NameError.NameTooShortError))
                it.name.length > 30 -> Validation.invalid(UserNameError(NameError.NameTooLongError))
                else -> Validation.Valid(it)
            }
        }

        val validatePassword: (User) -> Validations<UserError, User> = {
            when {
                it.password.isEmpty() -> Validation.invalid(UserPasswordError(PasswordError.PasswordEmptyError))
                it.password.length < 4 -> Validation.invalid(UserPasswordError(PasswordError.PasswordTooShortError))
                it.password.length > 30 -> Validation.invalid(UserPasswordError(PasswordError.PasswordTooLongError))
                else -> Validation.Valid(it)
            }
        }
    }

}

fun main(args: Array<String>) {
    val user = User("ouaiwheuiopaheupaSHEUOIPASHEUoipasheuiphasipehusaeuip", "")

    user.let(validateName + validatePassword).fold({
        it.map { println(it) }
    }, {
        println(it)
    })

}