package com.idealista.android.wrench.logger

import com.idealista.android.wrench.logger.Priority.ASSERT
import com.idealista.android.wrench.logger.Priority.DEBUG
import com.idealista.android.wrench.logger.Priority.ERROR
import com.idealista.android.wrench.logger.Priority.INFO
import com.idealista.android.wrench.logger.Priority.VERBOSE
import com.idealista.android.wrench.logger.Priority.WARN

class Logger(private val tag: String = "Poet",
             private val logFunction: (Int, String, String) -> Unit,
             private val logFormatter: LogFormatter = DefaultLogFormatter()) {

  fun d(message: String) {
    log(DEBUG(), message, null)
  }

  fun e(message: String, throwable: Throwable?) {
    log(ERROR(), message, throwable)
  }

  fun w(message: String) {
    log(WARN(), message, null)
  }

  fun i(message: String) {
    log(INFO(), message, null)
  }

  fun v(message: String) {
    log(VERBOSE(), message, null)
  }

  fun wtf(message: String) {
    log(ASSERT(), message, null)
  }

  @Synchronized private fun log(priority: Priority, message: String, throwable: Throwable?) {
    logFunction(priority.value, tag, logFormatter.format(message, throwable))
  }
}