package com.idealista.android.wrench.sample.domain.error

sealed class UseCaseError {

    object Network : UseCaseError()

}