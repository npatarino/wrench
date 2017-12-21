package com.idealista.android.wrench.sample.domain.chat

import com.idealista.android.wrench.sample.domain.chat.error.UseCaseChatError
import com.idealista.android.wrench.sample.domain.chat.model.Message
import com.idealista.android.wrench.sample.domain.repository.ChatRepository
import com.idealista.android.wrench.type.Either

fun sendMessage(chatRepository: ChatRepository): (Message) -> Either<UseCaseChatError, Message> =
        { chatRepository.sendMessage(it) }