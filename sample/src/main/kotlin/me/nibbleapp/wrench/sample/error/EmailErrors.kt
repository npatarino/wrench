package me.nibbleapp.wrench.sample.error


sealed class EmailErrors(val email: String) {

    class Empty(email: String) : EmailErrors(email)
    class Invalid(email: String) : EmailErrors(email)
    class TooLong(email: String) : EmailErrors(email)
    class TooShort(email: String) : EmailErrors(email)

}