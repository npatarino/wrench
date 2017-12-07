package com.idealista.android.wrench.sample.usecase.chat.error

import com.idealista.android.wrench.sample.error.UseCaseError

sealed class UseCaseChatError {

    class UseCase(val useCaseError: UseCaseError) : UseCaseChatError()
    class Chat(val chatError: ChatError) : UseCaseChatError()

}