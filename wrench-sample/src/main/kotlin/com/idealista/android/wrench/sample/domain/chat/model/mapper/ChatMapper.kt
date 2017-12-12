package com.idealista.android.wrench.sample.domain.chat.model.mapper

import com.idealista.android.wrench.sample.domain.chat.model.Message

fun Message.toModel(): String = "$sender: $text"