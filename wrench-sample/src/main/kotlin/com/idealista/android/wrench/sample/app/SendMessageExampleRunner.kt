package com.idealista.android.wrench.sample.app

import com.idealista.android.wrench.sample.app.messages.view.SendMessageActivity
import kotlinx.coroutines.experimental.delay
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {

    SendMessageActivity().onCreate()

    println("Hi")
    sleep(6000)
    println("Hi 2")

}

