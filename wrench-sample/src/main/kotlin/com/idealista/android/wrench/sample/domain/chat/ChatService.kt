package com.idealista.android.wrench.sample.domain.chat

import com.idealista.android.wrench.sample.domain.chat.error.UseCaseChatError
import com.idealista.android.wrench.sample.domain.chat.model.Message
import com.idealista.android.wrench.type.Either

fun sendMessage(): (Message) -> Either<UseCaseChatError, Message> = { Either.Right(it) }