package me.nibbleapp.wrench.logger

interface LogFormatter {

  val DEFAULT_STACK_LENGTH: Int

  fun format(message: String, throwable: Throwable?, depth: Int = DEFAULT_STACK_LENGTH): String

}