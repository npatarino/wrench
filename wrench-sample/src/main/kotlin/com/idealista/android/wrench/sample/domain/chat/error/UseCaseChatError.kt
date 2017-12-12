package com.idealista.android.wrench.sample.domain.chat.error

import com.idealista.android.wrench.sample.domain.error.UseCaseError

sealed class UseCaseChatError {

    class UseCase(val useCaseError: UseCaseError) : UseCaseChatError()
    class Chat(val chatError: ChatError) : UseCaseChatError()

}