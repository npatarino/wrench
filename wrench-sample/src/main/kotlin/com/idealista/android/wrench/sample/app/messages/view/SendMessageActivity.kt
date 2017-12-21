package com.idealista.android.wrench.sample.app.messages.view

import com.idealista.android.wrench.executor.coroutine.CoroutineExecutor
import com.idealista.android.wrench.sample.app.messages.presenter.SendMessagePresenter
import com.idealista.android.wrench.sample.app.model.mapper.messageMapper
import com.idealista.android.wrench.sample.data.repository.datasource.NetworkDataSource
import com.idealista.android.wrench.sample.domain.chat.model.Message
import com.idealista.android.wrench.sample.domain.chat.sendMessage
import com.idealista.android.wrench.sample.domain.repository.ChatRepository
import java.util.*


class SendMessageActivity : SendMessageView {

    private val useCaseExecutor = CoroutineExecutor()
    private val chatRepository = ChatRepository(NetworkDataSource())

    fun onCreate() {

        val sendMessagePresenter = SendMessagePresenter(this,
                sendMessage(chatRepository),
                messageMapper,
                useCaseExecutor)

        val message = Message("Vamos, vamos Argentina", "npatarino", Date().time)

        sendMessagePresenter.onSendClicked(message)
    }

    override fun showErrorMessage(message: String) = println(message)

    override fun showMessage(message: String) = println(message)

}
