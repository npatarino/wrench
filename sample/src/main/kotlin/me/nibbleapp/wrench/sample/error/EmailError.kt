package me.nibbleapp.wrench.sample.error


sealed class EmailError(val email: String) {

    class Empty(email: String) : EmailError(email)
    class Invalid(email: String) : EmailError(email)
    class TooLong(email: String) : EmailError(email)
    class TooShort(email: String) : EmailError(email)

}