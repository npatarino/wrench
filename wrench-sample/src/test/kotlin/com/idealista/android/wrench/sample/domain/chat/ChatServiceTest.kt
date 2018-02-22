package com.idealista.android.wrench.sample.domain.chat

import com.idealista.android.wrench.sample.data.repository.datasource.NetworkDataSource
import com.idealista.android.wrench.sample.domain.repository.ChatRepository
import com.idealista.android.wrench.sample.mothers.MessageObjectMother.Companion.messageValid
import org.junit.Assert.assertEquals
import org.junit.Test

class ChatServiceTest {

    @Test
    fun `given a sendMessage function when it's invoked it should return Right(message)`() {
        val sendMessage = sendMessage(ChatRepository(NetworkDataSource()))

        val result = sendMessage(messageValid)

        assertEquals(messageValid, result.get())
    }

}