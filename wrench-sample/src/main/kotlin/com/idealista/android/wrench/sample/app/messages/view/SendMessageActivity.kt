package com.idealista.android.wrench.sample.app.messages.view

import com.idealista.android.wrench.executor.DefaultExecutor
import com.idealista.android.wrench.sample.app.messages.presenter.SendMessagePresenter
import com.idealista.android.wrench.sample.domain.chat.model.Message
import com.idealista.android.wrench.sample.domain.chat.model.mapper.toModel
import com.idealista.android.wrench.sample.domain.chat.sendMessage
import java.util.*
import java.util.concurrent.ScheduledThreadPoolExecutor


class SendMessageActivity : SendMessageView {

    private val useCaseExecutor = DefaultExecutor(ScheduledThreadPoolExecutor(4))
    private val messageMapper: (Message) -> String = { it.toModel() }

    fun onCreate() {

        val sendMessagePresenter = SendMessagePresenter(this, sendMessage(), messageMapper, useCaseExecutor)

        val message = Message("Vamos, vamos Argentina", "npatarino", Date().time)

        sendMessagePresenter.onSendClicked(message)
    }

    override fun showMessage(message: String) {
        println(message)
    }
}
