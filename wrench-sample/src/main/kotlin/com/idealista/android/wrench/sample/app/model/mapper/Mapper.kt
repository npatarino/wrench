package com.idealista.android.wrench.sample.app.model.mapper

import com.idealista.android.wrench.sample.domain.chat.model.Message
import com.idealista.android.wrench.sample.domain.chat.model.mapper.toModel

val messageMapper: (Message) -> String = { it.toModel() }