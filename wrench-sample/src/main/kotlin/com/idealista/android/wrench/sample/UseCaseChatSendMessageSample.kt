package com.idealista.android.wrench.sample

import com.idealista.android.wrench.executor.DefaultExecutor
import com.idealista.android.wrench.sample.error.UseCaseError
import com.idealista.android.wrench.sample.model.chat.Message
import com.idealista.android.wrench.sample.model.chat.mapper.toModel
import com.idealista.android.wrench.sample.usecase.chat.error.ChatError
import com.idealista.android.wrench.sample.usecase.chat.error.UseCaseChatError
import com.idealista.android.wrench.sample.usecase.chat.sendMessage
import com.idealista.android.wrench.usecase.UseCase
import java.util.*
import java.util.concurrent.ScheduledThreadPoolExecutor

fun main(args: Array<String>) {

    val useCase = UseCase<UseCaseChatError, Message>()
            .bg(sendMessage(Message("Hello world!", "npatarino@idealista.com", Date().time)))
            .map { it.toModel() }
            .ui({ it.fold({ handleError(it) }, { handleSuccess(it) }) })

    useCase.run(DefaultExecutor(ScheduledThreadPoolExecutor(4)))

}

fun handleSuccess(message: String) {
    println(message)
}

fun handleError(error: UseCaseChatError): Unit = when (error) {
    is UseCaseChatError.UseCase -> when (error.useCaseError) {
        UseCaseError.Network -> println("Network error!")
    }
    
    is UseCaseChatError.Chat -> when (error.chatError) {
        ChatError.ConversationExpired -> println("Network error!")
        ChatError.ReceiverNotFound -> println("Network error!")
    }
}
