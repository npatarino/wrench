package me.nibbleapp.wrench.sample.error

sealed class SendEmailError {

    class NoNetworkError : SendEmailError()
    class ServerError : SendEmailError()
    class BadRequest : SendEmailError()

}