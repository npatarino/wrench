package me.nibbleapp.wrench.sample.validation

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