package com.idealista.android.wrench.sample.domain.repository

import com.idealista.android.wrench.sample.data.repository.datasource.NetworkDataSource
import com.idealista.android.wrench.sample.domain.chat.error.UseCaseChatError
import com.idealista.android.wrench.sample.domain.chat.model.Message
import com.idealista.android.wrench.type.Either

class ChatRepository(private val networkDataSource: NetworkDataSource) {

    fun sendMessage(message: Message): Either<UseCaseChatError, Message> = networkDataSource.sendMessage(message)

}