package com.idealista.android.wrench.sample.usecase.chat.error

sealed class ChatError {

    object ConversationExpired : ChatError()
    object ReceiverNotFound : ChatError()

}