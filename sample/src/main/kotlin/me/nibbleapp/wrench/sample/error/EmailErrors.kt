package me.nibbleapp.wrench.sample.error


sealed class EmailErrors {

    class Empty : EmailErrors()
    class Invalid : EmailErrors()
    class TooLong : EmailErrors()
    class TooShort : EmailErrors()

}