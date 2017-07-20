package me.nibbleapp.wrench.sample.error

sealed class SendEmailErrors {

    class NoNetworkError : SendEmailErrors()
    class ServerError : SendEmailErrors()
    class BadRequest : SendEmailErrors()

}