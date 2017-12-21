package com.idealista.android.wrench.sample.app.messages.presenter

import com.idealista.android.wrench.executor.SyncExecutor
import com.idealista.android.wrench.sample.app.messages.view.SendMessageView
import com.idealista.android.wrench.sample.app.model.mapper.messageMapper
import com.idealista.android.wrench.sample.domain.chat.error.ChatError
import com.idealista.android.wrench.sample.domain.chat.error.UseCaseChatError
import com.idealista.android.wrench.sample.domain.chat.model.Message
import com.idealista.android.wrench.sample.mothers.MessageObjectMother.Companion.messageValid
import com.idealista.android.wrench.type.Either
import com.nhaarman.mockito_kotlin.*
import org.junit.Test

class SendMessagePresenterTest {

    private val useCaseExecutor = SyncExecutor()

    private val sendMessageFunction = mock<(Message) -> Either<UseCaseChatError, Message>>()

    @Test
    fun `given a successful sendMessage function when call onSendClicked it should call view#showMessage`() {
        whenever(sendMessageFunction(any())).then { Either.Right(messageValid) }
        val viewMocked = mock<SendMessageView>()
        val sendMessagePresenter = SendMessagePresenter(viewMocked, sendMessageFunction, messageMapper, useCaseExecutor)

        sendMessagePresenter.onSendClicked(messageValid)

        then(viewMocked).should().showMessage(any())
    }

    @Test
    fun `given a failure sendMessage function when call onSendClicked it should call view#showErrorMessage`() {
        whenever(sendMessageFunction(any())).then { Either.Left(UseCaseChatError.Chat(ChatError.ReceiverNotFound)) }
        val viewMocked = mock<SendMessageView>()
        val sendMessagePresenter = SendMessagePresenter(viewMocked, sendMessageFunction, messageMapper, useCaseExecutor)

        sendMessagePresenter.onSendClicked(messageValid)

        then(viewMocked).should().showErrorMessage(any())
    }

}