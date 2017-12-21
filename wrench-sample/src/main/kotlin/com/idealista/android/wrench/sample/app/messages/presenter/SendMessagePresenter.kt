package com.idealista.android.wrench.sample.app.messages.presenter

import com.idealista.android.wrench.executor.UseCaseExecutor
import com.idealista.android.wrench.sample.app.messages.view.SendMessageView
import com.idealista.android.wrench.sample.domain.chat.error.ChatError
import com.idealista.android.wrench.sample.domain.chat.error.UseCaseChatError
import com.idealista.android.wrench.sample.domain.chat.model.Message
import com.idealista.android.wrench.sample.domain.error.UseCaseError
import com.idealista.android.wrench.type.Either
import com.idealista.android.wrench.usecase.UseCase
import com.idealista.android.wrench.usecase.UseCaseExecutable

class SendMessagePresenter(private val view: SendMessageView,
                           private val sendMessageFunction: (Message) -> Either<UseCaseChatError, Message>,
                           private val messageMapper: (Message) -> String,
                           private val useCaseExecutor: UseCaseExecutor) {

    companion object {
        val useCaseDelayInMillis = 5 * 1000L
    }

    var useCase: UseCaseExecutable<UseCaseChatError, String>? = null

    fun onSendClicked(message: Message) {
        useCase?.cancel()
        useCase = UseCase<UseCaseChatError, Message>()
                .bg({ sendMessageFunction(message) })
                .map { messageMapper(it) }
                .ui({ it.fold({ handleError(it) }, { handleSuccess(it) }) })
        useCase?.run(useCaseExecutor)
    }

    private fun handleSuccess(message: String) {
        view.showMessage(message)
    }

    private fun handleError(error: UseCaseChatError): Unit = when (error) {
        is UseCaseChatError.UseCase -> when (error.useCaseError) {
            UseCaseError.Network -> view.showErrorMessage("Network error!")
        }

        is UseCaseChatError.Chat -> when (error.chatError) {
            ChatError.ConversationExpired -> view.showErrorMessage("Network error!")
            ChatError.ReceiverNotFound -> view.showErrorMessage("Network error!")
        }
    }

}