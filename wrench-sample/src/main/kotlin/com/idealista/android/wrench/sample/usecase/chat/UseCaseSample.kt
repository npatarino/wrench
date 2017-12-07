package com.idealista.android.wrench.sample.usecase.chat

import com.idealista.android.wrench.sample.model.chat.Message
import com.idealista.android.wrench.sample.usecase.chat.error.UseCaseChatError
import com.idealista.android.wrench.type.Either

fun sendMessage(message: Message): () -> Either<UseCaseChatError, Message> = {
    Either.Right(message)
}