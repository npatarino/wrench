package com.idealista.android.wrench.sample.app

import com.idealista.android.wrench.sample.app.messages.view.SendMessageActivity
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) = runBlocking {

    SendMessageActivity().onCreate()

    delay(1000)
}

