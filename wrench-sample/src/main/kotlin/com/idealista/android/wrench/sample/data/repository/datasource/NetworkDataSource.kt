package com.idealista.android.wrench.sample.data.repository.datasource

import com.idealista.android.wrench.sample.domain.chat.error.UseCaseChatError
import com.idealista.android.wrench.sample.domain.chat.model.Message
import com.idealista.android.wrench.type.Either

class NetworkDataSource {

    fun sendMessage(message: Message): Either<UseCaseChatError, Message> = Either.Right(message)

}