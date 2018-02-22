package com.idealista.android.wrench.executor

interface CancellationToken {

    fun cancel(message: String = ""): Boolean

    companion object {
        val empty = object : CancellationToken {
            override fun cancel(message: String): Boolean = true
        }
    }

}