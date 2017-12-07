package com.idealista.android.wrench.sample.model.chat.mapper

import com.idealista.android.wrench.sample.model.chat.Message

fun Message.toModel(): String = "$sender: $text"