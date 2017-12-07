package com.idealista.android.wrench.sample.error

sealed class UseCaseError {

    object Network : UseCaseError()

}