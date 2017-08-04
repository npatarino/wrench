package me.nibbleapp.wrench.usecase

sealed class Error {
    class UnknownError : Error()
}