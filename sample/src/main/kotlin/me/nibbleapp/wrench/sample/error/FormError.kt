package me.nibbleapp.wrench.sample.error


sealed class FormError(val email: String) {

    class Empty(email: String) : FormError(email)
    class Invalid(email: String) : FormError(email)
    class TooLong(email: String) : FormError(email)
    class TooShort(email: String) : FormError(email)

}