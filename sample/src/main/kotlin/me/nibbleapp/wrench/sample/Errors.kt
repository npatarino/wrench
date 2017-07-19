package me.nibbleapp.wrench.sample


sealed class Errors {

    class Empty : Errors()
    class Invalid : Errors()
    class TooLong : Errors()
    class TooShort : Errors()

}