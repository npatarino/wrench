package com.idealista.android.wrench.sample.mothers

import com.idealista.android.wrench.sample.domain.chat.model.Message
import java.util.*

class MessageObjectMother {

    companion object {
        val messageValid = Message("Vamos, vamos Argentina", "Nico", Date().time)
    }

}