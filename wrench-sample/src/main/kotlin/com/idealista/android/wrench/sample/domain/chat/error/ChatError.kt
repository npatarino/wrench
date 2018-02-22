package com.idealista.android.wrench.sample.domain.chat.error

sealed class ChatError {

    object ConversationExpired : ChatError()
    object ReceiverNotFound : ChatError()

}